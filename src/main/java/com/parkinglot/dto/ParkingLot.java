package com.parkinglot.dto;

import com.parkinglot.exceptions.UnavailableParkingException;

import java.util.PriorityQueue;
import java.util.UUID;
import java.util.stream.IntStream;

public class ParkingLot {

  private final String id;
  private final int capacity;

  private final PriorityQueue<Integer> availableSlots;

  public ParkingLot(final int capacity) {
    throwWhenCapacityProvidedIsInvalid(capacity);
    id = UUID.randomUUID().toString();
    this.capacity = capacity;
    this.availableSlots = new PriorityQueue<>();
    IntStream.rangeClosed(1, capacity).forEach(availableSlots::offer);
  }

  private void throwWhenCapacityProvidedIsInvalid(final int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("invalid capacity " + capacity + " provided");
  }

  public int getCapacity() {
    return capacity;
  }

  public String getId() {
    return id;
  }

  public int getAvailableSlot() {
    if (isParkingFul()) {
      throw new UnavailableParkingException("Parking not available");
    }
    return availableSlots.poll();
  }

  public void freeOccupiedSlot(final int slotId) {
    availableSlots.offer(slotId);
  }

  public int getNumberOfAvailableFreeSlots() {
    return availableSlots.size();
  }

  public boolean isParkingFul() {
    return availableSlots.isEmpty();
  }
}
