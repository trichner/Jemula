/*
 * 
 * This is jemula.
 *
 *    Copyright (c) 2006-2009 Stefan Mangold, Fabian Dreier, Stefan Schmid
 *    All rights reserved. Urheberrechtlich geschuetzt.
 *    
 *    Redistribution and use in source and binary forms, with or without modification,
 *    are permitted provided that the following conditions are met:
 *    
 *      Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer. 
 *    
 *      Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation and/or
 *      other materials provided with the distribution. 
 *    
 *      Neither the name of any affiliation of Stefan Mangold nor the names of its contributors
 *      may be used to endorse or promote products derived from this software without
 *      specific prior written permission. 
 *    
 *    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 *    EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *    OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *    IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 *    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *    BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *    OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *    WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 *    OF SUCH DAMAGE.
 *    
 */

package statistics;

import java.util.Random;

import kernel.JEmula;

/**
 * @author Stefan Mangold (debugged by S. Geiger & M. Imhof)
 * 
 */
public final class JERandomVar extends JEmula {

	private String theDistr;

	private double theMin;

	private double theMean;

	private double theMax;


	/**
	 * @param aGenerator
	 * @param aDistribution
	 * @param aMinValue
	 * @param aMeanValue
	 */
	public JERandomVar(Random aGenerator, String aDistribution, double aMinValue, double aMeanValue) {

		this.theUniqueRandomGenerator = aGenerator;

		this.theDistr = new String(aDistribution);

		this.theMin = aMinValue;
		this.theMean = aMeanValue;
		if (this.theDistr.equals("NegExp")) {
			this.theMax = this.theMean * 1000.0; // approximation
		} else if (this.theDistr.equals("Uniform") | this.theDistr.equals("UniformInt")) {
			this.theMax = (this.theMin + this.theMean - this.theMin) * 2;

		} else {
			this.error("WARNING: unknown distribution '" + aDistribution + "' !!!");
		}
	}

	/**
	 * @param aCbrDistribution
	 * @param aMeanValue
	 */
	public JERandomVar(String aCbrDistribution, double aMeanValue) {

		this.theDistr = new String(aCbrDistribution);

		if (this.theDistr.equals("cbr")) {
			this.theMin = 0;
			this.theMean = aMeanValue;
			this.theMax = 0;

		} else {
			this.error("WARNING: unknown distribution '" + aCbrDistribution + "', eventually wrong constructor !!!");
		}
	}

	/**
	 * @param aRand
	 * @return Double
	 */
	private double uniform(double aRand) {
		return aRand * (this.theMax - this.theMin) + this.theMin;
	}

	/**
	 * @param aRand
	 * @return Double
	 */
	private double negexp(double aRand) {
		return Math.min(-Math.log(1 - aRand) * this.theMean, this.theMax);
	}

	/**
	 * @return next value
	 */
	public double nextvalue() {
		if (this.theDistr.equals("NegExp")) {
			double val = this.negexp(theUniqueRandomGenerator.nextDouble());
			return (val);

		} else if (this.theDistr.equals("Uniform")) {
			double nextDouble = this.uniform(theUniqueRandomGenerator.nextDouble());

			return nextDouble;

		} else if (this.theDistr.equals("UniformInt")) {
			int nextInt =(int) this.uniform(theUniqueRandomGenerator.nextDouble());
			//System.out.println(nextInt);
			return nextInt;
			
		} else if (this.theDistr.equals("cbr")) {
			return (this.theMean);
		} else {
			this.message("WARNING: unknown distribution !!!", 10);
			return 0.0;
		}
	}
}
