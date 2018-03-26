
public class Ball {
	
	private static final double MIN_SPEED = 1.0/12; 				// 1 in/s = 1/12 ft/s
	private static final double FRICTION_SPEED_LOSS_RATE = 0.01; 	// Rate of speed loss due to friction
	
	private double x, y, vx, vy, dt; 	// X & Y position in ft from center, X & Y velocity in ft/s, Time slice in seconds (default 1)
	private double r = 4.45/12; 		// Radius in ft
	private double weight = 1; 			// Weight in lbs
	
	
	public Ball( double x, double y ,double vx, double vy, double dt ) {
		this.x	= x;
		this.y	= y;
		this.vx	= vx;
		this.vy	= vy;
		this.dt	= dt;
	}
	
	
	// Update ball position and velocity over the time slice (dt) while accounting for friction
	public void move() {
		
		if ( getSpeed() >= MIN_SPEED ) {
			// Update positions
			x += vx * dt;
			y += vy * dt;
			
			// Update speed with frictional loss of 1% speed / second
			double rateSpeedLoss = dt * FRICTION_SPEED_LOSS_RATE; // rate = (dt)/(1 second) * (1% loss rate)
			double curSpeed = getSpeed();
			curSpeed *= ( 1 - rateSpeedLoss );
			
			// Update velocities
			double theta = Math.atan(vy/vx);
			vx = curSpeed * Math.cos( theta ) * Math.signum( vx );
			vy = curSpeed * Math.sin( theta ) * Math.signum( vy );
			
			// If speed is less than 1 in/s (1/12 ft/s), stop the ball
			if ( getSpeed() < MIN_SPEED ) {
				vx = 0;
				vy = 0;
			}
		}
	}
	
	
	// Speed = sqrt(vx^2 + vy^2)
	public double getSpeed() {
		return Math.sqrt( Math.pow(vx, 2) + Math.pow(vy, 2) );
	}
	
	
	// Get position and velocity values
	public double[] getStats() {
		return new double[] { x, y, vx, vy };
	}
	
	
	// Get formatted string of position and velocity
	public String getReport() {
		return String.format( "x  = %.4f%ny  = %.4f%nvx = %.4f%nvy = %.4f%n", x, y, vx, vy );
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}
	
	public double getRadius() {
		return r;
	}
	
	
	// Check if this ball collided with another ball b
	public boolean collidedWith( Ball b ) {
		double dx = x - b.getX(); // X distance between balls
		double dy = y - b.getY(); // Y distance between balls
		double distance = Math.sqrt( Math.pow(dx, 2) + Math.pow(dy, 2) ); // Euclidean distance between balls
		
		// If distance <= sum of both balls' radii, balls have collided (return true)
		return ( distance <= r + b.getRadius() );
	}
	
	

	public static void main( String[] args ) {
		// Use double primitives to init params (e.g. 1 in/s is 1.0/12 == 0.083, not 1/12 == 0 as an int)
		double x	  	  = 0;       // X position in feet from center
		double y 	  = 0;       // Y position in feet from center
		double vx	  = 1;       // X velocity in feet/second
		double vy	  = 0;	     // Y velocity in feet/second
		double dt	  = 0.01;       // Time slide in seconds (default 1)
		
		Ball b1 = new Ball( x, y, vx, vy, dt );
		Ball b2 = new Ball( 1, 0, 0, 0, dt );
		for ( int i = 0; i < 50; i++ ) {
			System.out.println( b1.getReport() );
			if ( b1.collidedWith(b2) ) {
				System.out.println( "COLLISION" );
			}
			b1.move();
		}
		System.out.println( b1.getReport() );
	}
}
