package dev.industrialreborn.energy;

/** Utility for one edge in a cable network. All values are EU per transfer tick. */
public final class EnergyTransfer {
    private EnergyTransfer() {}

    public record Result(long extracted, long delivered, long lost) {}

    /**
     * Transfers up to requested EU from source to target.
     * Loss is calculated as a percentage of extracted energy and rounded down.
     */
    public static Result transfer(EnergyStorage source, EnergyStorage target,
                                  long requested, int lossPercent) {
        if (lossPercent < 0 || lossPercent > 100) {
            throw new IllegalArgumentException("lossPercent must be in [0, 100]");
        }
        if (requested <= 0) return new Result(0, 0, 0);

        long possible = Math.min(requested, source.getMaxExtract());
        possible = Math.min(possible, source.getEnergy());
        if (possible == 0) return new Result(0, 0, 0);

        long theoreticalLoss = possible * lossPercent / 100;
        long theoreticalDelivery = possible - theoreticalLoss;
        long accepted = Math.min(theoreticalDelivery, target.getAvailableSpace());
        if (accepted <= 0) return new Result(0, 0, 0);

        // Determine the smallest source draw that can deliver the accepted amount.
        long draw = possible;
        if (lossPercent == 0) {
            draw = accepted;
        } else {
            while (draw > 0 && draw - (draw * lossPercent / 100) >= accepted) {
                draw--;
            }
            draw++;
        }
        draw = Math.min(draw, possible);
        long extracted = source.extract(draw, false);
        long loss = extracted * lossPercent / 100;
        long delivered = target.receive(Math.min(accepted, extracted - loss), false);
        // Any energy not delivered (including rounding/space edge cases) is accounted as lost.
        long actualLoss = extracted - delivered;
        return new Result(extracted, delivered, actualLoss);
    }
}
