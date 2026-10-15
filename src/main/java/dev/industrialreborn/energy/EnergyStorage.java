package dev.industrialreborn.energy;

/**
 * Server-side EU buffer. Amounts are expressed in Energy Units (EU).
 * This class deliberately has no Minecraft dependencies so it can be unit-tested.
 */
public final class EnergyStorage {
    private final long capacity;
    private final long maxReceive;
    private final long maxExtract;
    private long energy;

    public EnergyStorage(long capacity, long maxReceive, long maxExtract) {
        if (capacity < 0 || maxReceive < 0 || maxExtract < 0) {
            throw new IllegalArgumentException("Capacity and transfer limits must be non-negative");
        }
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    public synchronized long receive(long requested, boolean simulate) {
        if (requested <= 0) return 0;
        long accepted = Math.min(requested, Math.min(maxReceive, capacity - energy));
        if (!simulate) energy += accepted;
        return accepted;
    }

    public synchronized long extract(long requested, boolean simulate) {
        if (requested <= 0) return 0;
        long extracted = Math.min(requested, Math.min(maxExtract, energy));
        if (!simulate) energy -= extracted;
        return extracted;
    }

    public synchronized long getEnergy() { return energy; }
    public long getCapacity() { return capacity; }
    public long getMaxReceive() { return maxReceive; }
    public long getMaxExtract() { return maxExtract; }

    public synchronized void setEnergy(long amount) {
        energy = Math.max(0, Math.min(capacity, amount));
    }

    public synchronized long getAvailableSpace() { return capacity - energy; }
}
