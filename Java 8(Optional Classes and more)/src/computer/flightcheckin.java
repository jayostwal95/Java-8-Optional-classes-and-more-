//Java program to check timezones of connecting flights in java
package computer;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class flightcheckin {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Connecting flights from: ");
		String from = in.nextLine();
		ZoneId fromZone = ZoneId.of(from);
		System.out.println("Connecting flights to: ");
		String to = in.nextLine();
		ZoneId toZone = ZoneId.of(to);

		List<String> connectingLocations = Arrays.asList("Europe/London", "America/Los_Angeles", "Europe/Paris");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime fromTime = localDateTime.plusHours(3); //random time
		String zonedFromTime = fromTime.atZone(fromZone).format(formatter);
		
		Random rand = new Random();
		String connecting = connectingLocations.get(rand.nextInt(connectingLocations.size()));
		ZoneId connectingZone = ZoneId.of(connecting);
		LocalDateTime connectingTime = fromTime.plusHours(3); //random time
		String zonedConnectingTime = connectingTime.atZone(connectingZone).format(formatter);
		
		LocalDateTime toTime = connectingTime.plusHours(5); //random time
		String zonedToTime = toTime.atZone(toZone).format(formatter);
		
		System.out.println("Departure Time in departure location -  " + from + ": "+ zonedFromTime);
		System.out.println("Arrival Time in connecting location -  " + connecting + ": "+ zonedConnectingTime);
		System.out.println("Arrival Time at final destination -  " + to + ": "+ zonedToTime);

	}
}