import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    boolean isBooked;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }
}

class Booking {
    String customerName;
    int roomNumber;

    Booking(String customerName, int roomNumber) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Adding Rooms
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));

        int choice;

        do {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");

            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewRooms();
                    break;

                case 2:
                    bookRoom(sc);
                    break;

                case 3:
                    cancelBooking(sc);
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }

    static void viewRooms() {

        System.out.println("\n===== AVAILABLE ROOMS =====");

        for (Room room : rooms) {

            String status = room.isBooked ? "Booked" : "Available";

            System.out.println(
                    "Room No: " + room.roomNumber +
                            " | Category: " + room.category +
                            " | Status: " + status
            );
        }
    }

    static void bookRoom(Scanner sc) {

        System.out.print("\nEnter Room Number to Book: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo) {

                if (!room.isBooked) {

                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    room.isBooked = true;

                    bookings.add(new Booking(name, roomNo));

                    System.out.println("Room Booked Successfully!");

                    // Payment Simulation
                    System.out.println("Payment Successful!");

                } else {
                    System.out.println("Room Already Booked!");
                }

                return;
            }
        }

        System.out.println("Room Not Found!");
    }

    static void cancelBooking(Scanner sc) {

        System.out.print("\nEnter Room Number to Cancel Booking: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo && room.isBooked) {

                room.isBooked = false;

                bookings.removeIf(
                        booking -> booking.roomNumber == roomNo
                );

                System.out.println("Booking Cancelled Successfully!");

                return;
            }
        }

        System.out.println("Booking Not Found!");
    }

    static void viewBookings() {

        System.out.println("\n===== BOOKING DETAILS =====");

        if (bookings.isEmpty()) {
            System.out.println("No Bookings Available!");
            return;
        }

        for (Booking booking : bookings) {

            System.out.println(
                    "Customer Name: " + booking.customerName +
                            " | Room Number: " + booking.roomNumber
            );
        }
    }
}