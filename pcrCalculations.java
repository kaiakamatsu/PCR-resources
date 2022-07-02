class pcrCalculations {
    // type the following into the terminal:
    // javac pcrCaclulations.java
    // java pcrCalculations <genotype> <start toe number> <end toe number> <start2
    // toe number> <end2 toe number> <start3 toe number> <end3 toe number>
    // can keep the <start2 toe number> <end2 toe number> <start3 toe number>
    // <end3toe number> empty
    // currently works for HSF1, DNMT3A, FGD5, MX1CRE, NRAS, TGM2
    // automatically calculates for two sets of controls if the samples won't fit on
    // the 24 well row of the gel
    public static void main(String[] args) {
        System.out.println("Genotype: " + args[0]);
        int samples = countSamples(args);
        System.out.println(samples + " samples");
        int controls = countControls(args, samples);
        System.out.println(controls + " controls");
        int total = samples + controls + 2; // n+2 rule
        System.out.println(total + " total n multiplier");
        double H2O = 5.5 * total;
        String water = Double.toString(H2O);
        System.out.println(water + " uL of H20");
        double primer = 1.0 * total;
        String p = Double.toString(primer);
        System.out.println(p + " uL of primer");
        double GoTaq = 7.5 * total;
        String gt = Double.toString(GoTaq);
        System.out.println(gt + " uL of GoTaq");
    }

    static int countSamples(String[] args) {
        int end1 = Integer.parseInt(args[2]);
        int start1 = Integer.parseInt(args[1]);
        int end2 = 0;
        int start2 = 0;
        if (args.length > 3) {
            end2 = Integer.parseInt(args[4]) + 1;
            start2 = Integer.parseInt(args[3]);
        }
        int end3 = 0;
        int start3 = 0;
        if (args.length > 5) {
            end3 = Integer.parseInt(args[6]) + 1;
            start3 = Integer.parseInt(args[5]);
        }
        int end4 = 0;
        int start4 = 0;
        if (args.length > 7) {
            end4 = Integer.parseInt(args[8]) + 1;
            start4 = Integer.parseInt(args[7]);
        }
        int end5 = 0;
        int start5 = 0;
        if (args.length > 9) {
            end5 = Integer.parseInt(args[10]) + 1;
            start5 = Integer.parseInt(args[9]);
        }
        int end6 = 0;
        int start6 = 0;
        if (args.length > 11) {
            end6 = Integer.parseInt(args[12]) + 1;
            start6 = Integer.parseInt(args[11]);
        }
        int samples = ((end1 - start1) + 1) + (end2 - start2) + (end3 - start3) + (end4 - start4) + (end5 - start5)
                + (end6 - start6);
        return samples;
    }

    static int countControls(String[] args, int samples) {
        int controls = 0;
        if (args[0].equals("HSF1") || args[0].equals("NRAS") || args[0].equals("TGM2")) {
            controls = controls + 3;
        } else {
            controls = controls + 2;
        }
        if (samples > 21) {
            controls = controls * 2;
        }
        return controls;
    }
}