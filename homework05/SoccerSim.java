
public class SoccerSim {
	
	public static final double dt = 0.1; // Time step of simulation

	
	public static void main( String[] args ) {
		System.out.println( "Welcome to the soccer simulation!" );
		
//		args = new String[] {"0", "0", "0.9", "0", "1", "0", "0", "0", "10", "0", "0", "0", "10"}; // TEST
//		args = new String[] {"1"}; // TEST
		
		double timeSlice = 1;    // Time slice (default 1 second)
		int nArgs = args.length; // Number of input args
		
		// Run simulation only if args are valid
		if ( isInputValid(args) ) {
			
			// Parse time slice arg if it exists
			if ( nArgs % 4 == 1 ) { // Parse time slice arg
				timeSlice = Double.parseDouble( args[nArgs-1] );
			}
			
			// Initialize balls
			int nBalls = nArgs/4;
			Ball[] balls = new Ball[ nBalls ];
			
			for ( int iBall = 0; iBall < nBalls; iBall++ ) {
				double x  = Double.parseDouble( args[iBall*4 + 0] );
				double y  = Double.parseDouble( args[iBall*4 + 1] );
				double vx = Double.parseDouble( args[iBall*4 + 2] );
				double vy = Double.parseDouble( args[iBall*4 + 3] );
				balls[iBall] = new Ball( x, y, vx, vy, dt );
			}
			
			// Run simulation
			System.out.println( String.format( "Running simulation,%n  with a time slice of %f seconds%n  with a time step  of %f seconds%n%n", timeSlice, dt ) );
			runSimulation( balls, timeSlice, dt );
		}
	}
	
	
	// Runs the simulation and prints reports
	public static void runSimulation( Ball[] balls, double timeSlice, double dt ) {
		
		// Report initial conditions
		System.out.println( "-------------------- Initial Report --------------------" );
		System.out.println( "Time: 0.0 seconds\n" + getReport(balls) );
		
		// Initialize simulation
		double curTime = 0;
		boolean foundCollision = false;
		String collisionReport = String.format( "No collision is possible\n\nTime: %f seconds\n", timeSlice );
		System.out.println( "------------------ Running Simulation ------------------" );
		
		// Run simulation until timeSlice
		while ( curTime < timeSlice ) {
			// Move all balls
			moveAllBalls( balls );
			
			// Check for collisions
			if ( collisionOccurred(balls) && !foundCollision ) {
				System.out.println( "Found first collision" );
				
				collisionReport = String.format( "Collision Found!\n\nTime: %f seconds\n%s", curTime, getCollisionReport(balls) );
				foundCollision = true;
			}
			
			// Report current conditions
			System.out.println( String.format("Time: %f seconds\n%s", curTime, getReport(balls)) );
			
			// Increment curTime by dt
			curTime += dt;
		}
		
		// Print collision report
		System.out.println( "------------------- Collision Report -------------------" );
		System.out.println( collisionReport );
	}
	
	
	// Returns a formatted report with balls involved, their positions, and their velocities (standard format)
	public static String getCollisionReport( Ball[] balls ) {
		String report = "Ball | X Position | Y Position | X Velocity | Y Position\n";
		boolean[] collided = flagCollisions( balls );
		
		// Add ball to report only if it is involved in a collision
		for ( int iBall = 0; iBall < balls.length; iBall++ ) {
			Ball b = balls[iBall];
			if ( collided[iBall] ) {
				report += String.format( "%4d | %10.4f | %10.4f | %10.4f | %10.4f%n", iBall+1, b.getX(), b.getY(), b.getVx(), b.getVy() );
			}
		}
		
		return report;
	}
	
	
	// Returns true if any element in the boolean array is true
	public static boolean any( boolean[] boolArray ) {
		for ( int i = 0; i < boolArray.length; i++ ) {
			if ( boolArray[i] ) {
				return true;
			}
		}
		return false;
	}
	
	
	// Returns true if any collisions occurred
	public static boolean collisionOccurred( Ball[] balls ) {
		return any( flagCollisions(balls) );
	}
	
	
	// Returns a boolean array where each element tells whether the corresponding ball in the balls array is involved in a collision
	public static boolean[] flagCollisions( Ball[] balls ) {
		
		// Initialize collided array
		boolean[] collided = new boolean[ balls.length ];
		for ( int i = 0; i < collided.length; i++ ) {
			collided[i] = false;
		}
		
		// Find pairwise collisions, set flags in collided array
		for ( int i = 0; i < balls.length - 1; i++ ) {
			for ( int j = i + 1; j < balls.length; j++ ) {
				if ( balls[i].collidedWith(balls[j]) ) {
					collided[i] = true;
					collided[j] = true;
				}
			}
		}
		
		return collided;
	}
	
	
	// Move all balls in the balls array
	public static void moveAllBalls( Ball[] balls ) {
		for ( int iBall = 0; iBall < balls.length; iBall++ ) {
			balls[iBall].move();
		}
	}
	
	
	// Get standard report of all balls with ball ID, position, and velocity
	public static String getReport(Ball[] balls) {
		String report = "Ball | X Position | Y Position | X Velocity | Y Position\n";
		for ( int iBall = 0; iBall<balls.length; iBall++ ) {
			Ball b = balls[iBall];
			report += String.format( "%4d | %10.4f | %10.4f | %10.4f | %10.4f%n", iBall+1, b.getX(), b.getY(), b.getVx(), b.getVy() );
		}
		return report;
	}
	
	
	// Check if simulation input arguments are valid
	public static boolean isInputValid( String[] args ) {
		boolean tf = true;
		
		// Check number of input args
		if ( (args.length % 4) != 0 && (args.length % 4) != 1 ) {
			System.out.println( "Error: Invalid number of command line arguments. Must enter 4n or 4n+1 number of command line args, where n is an integer >= 0." );
			System.out.println( args.length + " arguments were entered." );
			tf = false;
		}
		
		if ( args.length == 0 || args.length == 1 ) { // Zero balls
			System.out.println( "Error: Zero balls entered. Must enter at least one ball." );
			tf = false;
		}
		
		// Check if args can all be converted to double
		try {
			for ( int i = 0; i < args.length; i++ ) {
				Double.parseDouble(args[i]);
			}
		} catch( NumberFormatException e ) { // Failed to convert an arg to double
			System.out.println( "Error: Invalid input format. Must enter numbers that can be converted to type double." );
			System.out.println( "NumberFormatException: " + e.getMessage() );
			System.out.println( "Later arguments may be invalid." ); // Breaks for loop upon first exception, does not check later args
			tf = false;
		}
		
		// Check time slice value
		if ( args.length % 4 == 1 ) {
			try {
				double timeSlice = Double.parseDouble( args[args.length-1] );
				if ( timeSlice <= 0 ) {
					System.out.println( "Error: Invalid input value. Time slice value must be a positive real number." );
					System.out.println( "You entered: " + timeSlice );
					tf = false;
				} else if (timeSlice < dt) {
					System.out.println( "Error: Invalid input value. Time slice value must be at least " + dt );
					System.out.println( "You entered: " + timeSlice );
					tf = false;
				}
			} catch( NumberFormatException e ) {
				
			}
		}
		
		return tf;
	}
}
