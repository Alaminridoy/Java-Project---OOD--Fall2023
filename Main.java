import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String carID;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carID, String brand, String model, double basePricePerDay){
        this.carID=carID;
        this.brand=brand;
        this.model=model;
        this.basePricePerDay=basePricePerDay;
        this.isAvailable=true;
    }

    public String getCarID(){
        return carID;
    }

    public String getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public double calculationPrice(int rentalDays){
        return basePricePerDay*rentalDays;
    }

    public boolean isAvailable(){
        return isAvailable;
    }
    public void rent(){
        isAvailable=false;
    }

    public void returnCar(){
        isAvailable=true;
    }
}

class Bike {
    private String bikeID;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Bike(String bikeID, String brand, String model, double basePricePerDay){
        this.bikeID=bikeID;
        this.brand=brand;
        this.model=model;
        this.basePricePerDay=basePricePerDay;
        this.isAvailable=true;
    }

    public String getBikeID(){
        return bikeID;
    }

    public String getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public double calculationPrice(int rentalDays){
        return basePricePerDay*rentalDays;
    }

    public boolean isAvailable(){
        return isAvailable;
    }
    public void rent(){
        isAvailable=false;
    }

    public void returnBike(){
        isAvailable=true;
    }
}
class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}
class Rental{
    private Car car;
    private Bike bike;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car=car;
        this.customer=customer;
        this.days=days;
    }
    public Rental(Bike bike, Customer customer, int days) {
        this.bike=bike;
        this.customer=customer;
        this.days=days;
    }

    public Car getCar() {
        return car;
    }

    public Bike getBike() {
        return bike;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}
class VehicleRantalSystem{
    private List<Car> cars;
    private List<Bike> bikes;
    private List<Customer> customers;
    private List<Rental> rentals;

    public VehicleRantalSystem(){
        cars= new ArrayList<>();
        bikes= new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }
    public void addCar(Car car){
        cars.add(car);
    }
    public void addBike(Bike bike){
        bikes.add(bike);
    }
    public void addcustomer(Customer customer){
        customers.add(customer);
    }
    public  void rentCar(Car car, Customer customer, int days){
        if (car.isAvailable()){
            rentals.add(new Rental(car,customer,days));
            car.rent();
        }else {
            System.out.println("Car is not available for rent.");
        }
    }

    public  void rentBike(Bike bike, Customer customer, int days){
        if (bike.isAvailable()){
            rentals.add(new Rental(bike,customer,days));
            bike.rent();
        }else {
            System.out.println("Bike is not available for rent.");
        }
    }

    public void returnCar(Car car){
        Rental rentalToRemove=null;
        for (Rental rental: rentals){
            if (rental.getCar()==car){
                rentalToRemove=rental;
                break;
            }
        }
        car.returnCar();

        if (rentalToRemove!=null){
            rentals.remove(rentalToRemove);
        }else {
            System.out.println("Car was not rented.");
        }
    }
    public void returnBike(Bike bike){
        Rental rentalToRemove=null;
        for (Rental rental: rentals){
            if (rental.getBike()==bike){
                rentalToRemove=rental;
                break;
            }
        }
        bike.returnBike();

        if (rentalToRemove!=null){
            rentals.remove(rentalToRemove);
        }else {
            System.out.println("Bike was not rented.");
        }
    }
    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("        Welcome to the Vehicle Rental System. ");
            System.out.println("        1. Rent a Car");
            System.out.println("        2. Rent a Bike");
            System.out.println("        3. Return Your Rent Car");
            System.out.println("        4. Return Your Rent Bike");
            System.out.println("        5. Exit");
            System.out.print("        Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("\n        Choose Your needed Car,which you want to Rent\n");
                System.out.println("\n        Brand - Model - Price\n        Mersidis-Benz - s400 - 200/perDay \n        BMW - F02 - 150/perDay\n        Honda - Civic2022 - 100/perDay\n        Toyota - CH-R - 50/perDay");
                System.out.println();
                System.out.print("        Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println("\n        Available Cars in our system :");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println("        "+car.getCarID() + " - " + car.getBrand() + " - " + car.getModel());
                    }
                }
                System.out.print("\n        Enter the car ID you want to rent: ");
                String carId = scanner.nextLine();

                System.out.print("        Enter the number of days for rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Customer newCustomer = new Customer("        CUSTOMER NO - " + (customers.size() + 1), customerName);
                addcustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCarID().equals(carId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }
                if (selectedCar != null) {

                    double totalPrice = selectedCar.calculationPrice(rentalDays);
                    System.out.println("\n        Rental Information\n");
                    System.out.println("        Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("        Customer Name: " + newCustomer.getName());
                    System.out.println("        Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("        Rental Days: " + rentalDays);
                    System.out.printf("        Total Price: $%.2f%n", totalPrice);

                    System.out.print("\n        Are you Confirm of the rent (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\n        This Car is rented successfully.");
                    } else {
                        System.out.println("\n        Rental canceled.");
                    }
                } else {
                    System.out.println("\n        Invalid car selection or car not available for rent.");
                }
            }else if (choice == 2) {
                System.out.println("\n        Choose Your needed Bike,which you want to Rent\n");
                System.out.println("\n        Brand - Model - Price\n        Yamaha - R15 - 200/perDay \n        Suzuki - Gixxer-SF - 15/perDay\n        Honda - CB150R ExMotion - 20/perDay\n        Pulser - 150 - 10/perDay");
                System.out.println();
                System.out.print("        Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println("\n        Available Bikes in our system :");
                for (Bike bike : bikes) {
                    if (bike.isAvailable()) {
                        System.out.println("        "+bike.getBikeID()+ " - " + bike.getBrand() + " - " + bike.getModel());
                    }
                }
                System.out.print("\n        Enter the Bike ID you want to rent: ");
                String bikeId = scanner.nextLine();

                System.out.print("        Enter the number of days for rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Customer newCustomer = new Customer("CUSTOMER NO - " + (customers.size() + 1), customerName);
                addcustomer(newCustomer);

                Bike selectedBike = null;
                for (Bike bike : bikes) {
                    if (bike.getBikeID().equals(bikeId) && bike.isAvailable()) {
                        selectedBike = bike;
                        break;
                    }
                }
                if (selectedBike != null) {

                    double totalPrice = selectedBike.calculationPrice(rentalDays);
                    System.out.println("\n        Rental Information\n");
                    System.out.println("        Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("        Customer Name: " + newCustomer.getName());
                    System.out.println("        Bike: " + selectedBike.getBrand() + " " + selectedBike.getModel());
                    System.out.println("        Rental Days: " + rentalDays);
                    System.out.printf("        Total Price: $%.2f%n", totalPrice);

                    System.out.print("\n        Are your Confirm your rent (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentBike(selectedBike, newCustomer, rentalDays);
                        System.out.println("\n        This Bike is rented successfully.");
                    } else {
                        System.out.println("\n        Rental canceled.");
                    }
                } else {
                    System.out.println("\n        Invalid bike selection or bike not available for rent.");
                }
            } else if (choice == 3) {
                System.out.println("\n        Return your Car,which you rented from Vehicle Rental System\n");
                System.out.print("        Enter the car ID you want to return: ");
                String carId = scanner.nextLine();

                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarID().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("        Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("        Car was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("        Invalid car ID or car is not rented.");
                }
            }else if (choice == 4) {
                System.out.println("\n        Return your Bike,which you rented from us\n");
                System.out.print("        Enter the bike ID you want to return: ");
                String bikeId = scanner.nextLine();

                Bike bikeToReturn = null;
                for (Bike bike : bikes) {
                    if (bike.getBikeID().equals(bikeId) && !bike.isAvailable()) {
                        bikeToReturn = bike;
                        break;
                    }
                }

                if (bikeToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getBike() == bikeToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnBike(bikeToReturn);
                        System.out.println("        Bike returned successfully by " + customer.getName());
                    } else {
                        System.out.println("        Bike was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("        Invalid Bike ID or Bike is not rented.");
                }
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("        Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("\n        Thank you for using the Vehicle Rental System!");
    }
}
public class Main {
    public static void main(String[] args) {

        VehicleRantalSystem rentalSystem = new VehicleRantalSystem();

        Car car1 = new Car("C001", "Mersidis-Benz", "s400", 200.0); // Different base price per day for each car
        Car car2 = new Car("C002", "BMW", "F02", 150.0);
        Car car3 = new Car("C003", "Honda", "Civic2022", 100.0);
        Car car4 = new Car("C004", "Toyota", "CH-R", 50.0);
        Bike bike1 = new Bike("B001", "Yamaha", "R15", 30.0);
        Bike bike2 = new Bike("B002", "Suzuki", "Gixxer-SF", 15.0);
        Bike bike3 = new Bike("B003", "Honda", "CB150R ExMotion", 20.0);
        Bike bike4 = new Bike("B004", "Pulser", "150", 10.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addBike(bike1);
        rentalSystem.addBike(bike2);
        rentalSystem.addBike(bike3);
        rentalSystem.addBike(bike4);


        rentalSystem.menu();
        }
    }
