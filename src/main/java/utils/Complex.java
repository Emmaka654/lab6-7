package utils;

public class Complex {
    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    private double re;
    private double im;

    public void plus(Complex other){
        this.re += other.re;
        this.im += other.im;
    }

    public static Complex plus( Complex c1, Complex c2){
        return new Complex(c1.re+c2.re, c1.im+c2.im);
    }

    public void minus(Complex other){
        this.re -= other.re;
        this.im -= other.im;
    }

    public static Complex minus( Complex c1, Complex c2){
        return new Complex(c1.re-c2.re, c1.im-c2.im);
    }

    public void mult(Complex other){
        re *= other.re;
        im *= other.im;
    }

    public static Complex mult( Complex c1, Complex c2){
        return new Complex(c1.re*c2.re, c1.im*c2.im);
    }

    public void X(double x){
        this.re *= x;
        this.im *= x;
    }

    public void print(){
        if(this.im <0){
            System.out.println(this.re+" - "+Math.abs(this.im)+"i");
        }
        if(this.im >0){
            System.out.println(this.re+" + "+this.im+"i");
        }
    }

    public Complex() {
        this.re += 0;
        this.im += 0;
    }

    public Complex(double re) {
        this.re += re;
        this.im += 0;
    }

    public Complex(double re, double im) {
        this.re += re;
        this.im += im;
    }

}

