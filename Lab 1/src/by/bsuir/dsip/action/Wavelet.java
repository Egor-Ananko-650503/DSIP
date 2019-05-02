package by.bsuir.dsip.action;

import by.bsuir.dsip.bean.Complex;

public class Wavelet {

    // Calculates maximum number of cycles we can get from a Haar matrix
    public int getHaarMaxCycles (int hw){
        int cycles = 0;
        while(hw > 1){
            cycles++;
            hw /= 2;
        }
        return cycles;
    }

    // Checks whether we can apply the desired number of cycles on Haar matrix
    public boolean isCycleAllowed(int maxCycle, int cycles){
        return cycles <= maxCycle;
    }

    // DFW
    public Complex[] DFW (Complex[] pixels, int cycles){
        int w = pixels.length;
        int maxCycle = getHaarMaxCycles(w);
        boolean isCycleAllowed = isCycleAllowed(maxCycle, cycles);
        if(isCycleAllowed){
            Complex[] tempPixels = new Complex[w];
            for(int i = 0; i < cycles; i++){
                for(int j = 0; j < w; j++){
                    tempPixels[j] = (pixels[2 * j].plus(pixels[2 * j + 1])).scale(1/2);
                    tempPixels[j + w] = (pixels[2 * j].minus(pixels[2 * j + 1])).scale(1/2);
                }
                w /= 2;
            }
            return tempPixels;
        }
        return null;
    }

    //DIW
    public Complex[] DIW (Complex[] pixels, int cycles){
        int w = pixels.length;
        int maxCycle = getHaarMaxCycles(w);
        boolean isCycleAllowed = isCycleAllowed(maxCycle, cycles);
        if(isCycleAllowed){
            Complex[] newPixels = new Complex[w];
            for (int i = 0; i < cycles; i++){
                for (int j = 0; j < w; j++){
                    newPixels[2 * j] = (pixels[j].minus(pixels[j + w])).scale(1/2);
                    newPixels[2 * j + 1] = (pixels[j].plus(pixels[j + w])).scale(1/2);
                }
                w /= 2;
            }
            return newPixels;
        }
        return null;
    }

}
