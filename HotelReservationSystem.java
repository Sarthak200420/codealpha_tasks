import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    public enum PaymentMethod {
        CASH, CREDIT_CARD, DEBIT_CARD
    }

    public static class Room {
        private int roomNumber;
        private String category;
        private boolean isAvailable;
        private String guestName;
        private PaymentMethod paymentMethod;

        public Room(int roomNumber, String category) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.isAvailable = true;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public String getCategory() {
            return category;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }

        public String getGuestName() {
            return guestName;
        }

        public void setGuestName(String guestName) {
            this.guestName = guestName;
        }

        public PaymentMethod getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
        }
    }

    public static class Hotel {
        private String name;
        private List<Room> rooms;

        public Hotel(String name) {
            this.name = name;
            this.rooms = new ArrayList<>();
        }

        public void addRoom(Room room) {
            rooms.add(room);
        }

        public Room getAvailableRoom(String category) {
            for (Room room : rooms) {
                if (room.isAvailable() && room.getCategory().equals(category)) {
                    return room;
                }
            }
            return null;
        }

        public void makeReservation(Room room, String guestName, PaymentMethod paymentMethod) {
            if (room.isAvailable()) {
                room.setAvailable(false);
                room.setGuestName(guestName);
                room.setPaymentMethod(paymentMethod);
                System.out.println("Reservation made for " + guestName + " in room " + room.getRoomNumber());
            } else {
                System.out.println("Room is not available");
            }
        }

        public void viewBookingDetails() {
            for (Room room : rooms) {
                if (!room.isAvailable()) {
                    System.out.println("Room " + room.getRoomNumber() + " is booked by " + room.getGuestName()
                            + " with payment method " + room.getPaymentMethod());
                }
            }
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel("My Hotel");

        // Add rooms to hotel
        hotel.addRoom(new Room(1, "Single"));
        hotel.addRoom(new Room(2, "Double"));
        hotel.addRoom(new Room(3, "Suite"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter room category: ");
                    String category = scanner.next();
                    Room room = hotel.getAvailableRoom(category);
                    if (room != null) {
                        System.out.println("Room " + room.getRoomNumber() + " is available");
                    } else {
                        System.out.println("No available rooms in that category");
                    }
                    break;
                case 2:
                    System.out.print("Enter room category: ");
                    category = scanner.next();
                    room = hotel.getAvailableRoom(category);
                    if (room != null) {
                        System.out.print("Enter guest name: ");
                        String guestName = scanner.next();
                        System.out.print("Enter payment method (CASH, CREDIT_CARD, DEBIT_CARD): ");
                        PaymentMethod paymentMethod = PaymentMethod.valueOf(scanner.next());
                        hotel.makeReservation(room, guestName, paymentMethod);
                    } else {
                        System.out.println("No available rooms in that category");
                    }
                    break;
                case 3:
                    hotel.viewBookingDetails();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}