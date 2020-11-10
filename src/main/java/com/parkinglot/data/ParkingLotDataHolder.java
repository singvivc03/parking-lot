package com.parkinglot.data;

import com.parkinglot.dto.ParkingLot;
import com.parkinglot.dto.Rule;
import com.parkinglot.dto.SlotDto;
import com.parkinglot.dto.Vehicle;
import com.parkinglot.exceptions.InvalidSlotSelectionException;
import com.parkinglot.exceptions.UnavailableParkingException;
import com.parkinglot.strategy.EvenDistributionParkingStrategy;
import com.parkinglot.strategy.FillFirstParkingStrategy;
import com.parkinglot.strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class ParkingLotDataHolder {

  private List<ParkingLot> parkingLots = new ArrayList<>();
  private Map<String, Set<SlotDto>> parkedVehicleVsSlotInfo = new HashMap<>();
  private ParkingStrategy parkingStrategy;

  public boolean createParkingLot(final int capacity) {
    var parkingLot = new ParkingLot(capacity);
    return parkingLots.add(parkingLot);
  }

  public void determineStrategyByRule(final Rule rule) {
    switch (rule) {
      case EVEN_DISTRIBUTION:
        parkingStrategy = new EvenDistributionParkingStrategy();
        break;
      case FILL_FIRST:
        parkingStrategy = new FillFirstParkingStrategy();
        break;
      default:
        throw new IllegalArgumentException("Invalid rule");
    }
  }

  public SlotDto parkVehicle(final Vehicle vehicle) {
    var optionalParkingLot = parkingStrategy.determineParkingLot(parkingLots);
    var parkingLot = optionalParkingLot.orElseThrow(() -> new UnavailableParkingException("No parking is available"));
    int slotId = parkingLot.getAvailableSlot();
    var slotInfo = new SlotDto(slotId, parkingLot.getId(), vehicle);
    var slots = parkedVehicleVsSlotInfo.getOrDefault(parkingLot.getId(), new HashSet<>());
    slots.add(slotInfo);
    parkedVehicleVsSlotInfo.put(parkingLot.getId(), slots);
    return slotInfo;
  }

  public List<ParkingLot> getParkingLots() {
    return this.parkingLots;
  }

  public Set<SlotDto> getAllBookedSlot() {
    return parkedVehicleVsSlotInfo.values().parallelStream().flatMap(Collection::parallelStream)
            .collect(toSet());
  }

  public Vehicle unPark(final int slot) {
    var occupiedSlots = getAllBookedSlot();
    var optionalSlotToFree = occupiedSlots.parallelStream().filter(it -> it.getId() == slot).findFirst();
    var slotInfo = optionalSlotToFree.orElseThrow(() -> new InvalidSlotSelectionException("invalid slot id"));
    occupiedSlots.remove(slotInfo);
    var parkingLotVsParkingId = parkingLots.parallelStream()
            .collect(toMap(ParkingLot::getId, self -> self));
    var parkingLot = parkingLotVsParkingId.get(slotInfo.getParkingId());
    parkingLot.freeOccupiedSlot(slotInfo.getId());
    return slotInfo.getParkedVehicle();
  }

  public String getParkingStatus() {
    var occupiedSlots = getAllBookedSlot();
    StringBuilder statusBuilder = new StringBuilder();
    statusBuilder.append("Slot No.").append("       Registration No").append("       Color\n");
    occupiedSlots.forEach(slot -> {
      statusBuilder.append(slot.getId() + "              ")
        .append(slot.getParkedVehicle().getRegistrationNumber() + "         ")
        .append(slot.getParkedVehicle().getColor());
      statusBuilder.append("\n");
    });
    return statusBuilder.toString();
  }
}
