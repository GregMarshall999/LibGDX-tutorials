package com.gdx.tutorials.FLGT.engine.noise;

import java.util.Random;

public class SimplexNoise {
    NoiseGenerator[] octaves;
    double[] frequencies;
    double[] amplitudes;

    int largeFeature;
    double persistence;
    int seed;

    public SimplexNoise(int largeFeature, double persistence, int seed) {
        this.largeFeature = largeFeature;
        this.persistence = persistence;
        this.seed = seed;

        int octavesCount = (int) Math.ceil(Math.log10(largeFeature)/Math.log10(2));
        System.out.println(octavesCount);

        octaves = new NoiseGenerator[octavesCount];
        frequencies = new double[octavesCount];
        amplitudes = new double[octavesCount];

        Random random = new Random(seed);

        for (int i = 0; i < octavesCount; i++) {
            octaves[i] = new NoiseGenerator(random.nextInt());
            frequencies[i] = Math.pow(2, i);
            System.out.println("F="+frequencies[i]+" "+i);
            amplitudes[i] = Math.pow(persistence, octaves.length-i)/2;
            System.out.println("A="+amplitudes[i]+" "+i);
        }
    }

    public double getNoise(int x, int y) {
        double result = 0;

        for(int i = 0; i < octaves.length; i++)
            result = result + octaves[i].noise(x/frequencies[i], y/frequencies[i]) * amplitudes[i];

        return result;
    }

    public double getNoise(int x,int y, int z) {
        double result=0;

        for(int i = 0; i < octaves.length; i++){
            double frequency = Math.pow(2, i);
            double amplitude = Math.pow(persistence, octaves.length - i);
            result = result + octaves[i].noise(x/frequency, y/frequency,z/frequency) * amplitude;
        }

        return result;
    }

    public double getSingleNoise(int x, int y, int z){
        NoiseGenerator octave = new NoiseGenerator(0);
        return octave.noise(x,y,z);
    }

    public double getDoubleNoise(int x, int y, int z){
        NoiseGenerator octave = new NoiseGenerator(0);
        NoiseGenerator octave2 = new NoiseGenerator(1);

        return (octave.noise(x,y,z) + (octave2.noise(x,y,z) /2)) /2;
    }

    public double getQuadNoise(int x, int y, int z){
        NoiseGenerator octave = new NoiseGenerator(0);
        NoiseGenerator octave2 = new NoiseGenerator(10);
        NoiseGenerator octave3 = new NoiseGenerator(20);
        NoiseGenerator octave4 = new NoiseGenerator(40);

        return (octave.noise(x,y,z) + (octave2.noise(x,y,z) /2) +
                (octave3.noise(x,y,z) /4) + (octave4.noise(x,y,z) /8)) /4;
    }

    public double getOctNoise(int x, int y, int z){
        NoiseGenerator octave = new NoiseGenerator(0);
        NoiseGenerator octave2 = new NoiseGenerator(10);
        NoiseGenerator octave3 = new NoiseGenerator(20);
        NoiseGenerator octave4 = new NoiseGenerator(40);
        NoiseGenerator octave5 = new NoiseGenerator(80);
        NoiseGenerator octave6 = new NoiseGenerator(100);
        NoiseGenerator octave7 = new NoiseGenerator(120);
        NoiseGenerator octave8 = new NoiseGenerator(140);

        return (octave.noise(x,y,z) + (octave2.noise(x,y,z) /2) + (octave3.noise(x,y,z) /4) +
                (octave4.noise(x,y,z) /8) + (octave5.noise(x,y,z) /16) + (octave6.noise(x,y,z) /32) +
                (octave7.noise(x,y,z) /64) + (octave8.noise(x,y,z) /128)) / 8;
    }
}
