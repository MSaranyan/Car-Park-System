import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
abstract class  Vehicle{
    public int IDplate;
    public String Brand;
    public String color;
    public static ArrayList<String> Parked_Vehicle_EntryData = new ArrayList<String>(20);
    abstract String type_vehicle();
    abstract String getColor();
    abstract int ID_plate();  
    abstract String what_datetime_enter(); 
    public abstract String toString();
}

class Car extends Vehicle{
    public int no_doors;
    String Brand = "Car";
    public Date date_time_enterCar;
    Car(int no_doors, String color, int IDplate){
        this.no_doors = no_doors;
        this.color = color;
        date_time_enterCar = new Date();
        this.IDplate = IDplate;
    }
    public int No_doors(){
        return this.no_doors;//GIVE INFORMATION ABOUT NUMBER OF DOORS
    }

    public String getColor(){ //GIVE INFORMATION ABOUT THE COLOUR OF THE VEHICLE
        return color;
    }
    public String what_datetime_enter(){
        return "Date and time :-" + Date_time.getdate_time(date_time_enterCar);//GIVE INFORMATION ABOUT THE ENTRY DATE/TIME
    }
    public int ID_plate(){ //GIVE INFORMATION ABOUT  ID NUMBER OF  THE VEHICLE
        return this.IDplate;
    }
    public String type_vehicle(){  //GIVE INFORMATION ABOUT  BRAND OF  THE VEHICLE
        return this.Brand;
    }

    public String toString(){
        return "The Vehicle : "+ type_vehicle() + " ID number : " + this.IDplate + " of color- " + this.color + " with " + this.no_doors + " number of doors " + " Entered Vehicle park at : " + what_datetime_enter();
    }
}


class Van extends Vehicle{
    public int no_doors;
    public int no_seats;
    String Brand = "Van";
    public Date date_time_enterVan;
    Van(int no_doors, String color, int IDplate, int no_seats){
        this.no_doors = no_doors;
        this.color = color;
        this.no_seats = no_seats;
        this.IDplate = IDplate;
        date_time_enterVan = new Date();
    }
    public int No_seats(){  //GIVE INFORMATION ABOUT THE NUMBER OF SEATS IN THE VEHICLE
        return this.no_seats;
    }
    public String type_vehicle(){  //GIVE INFORMATION ABOUT  BRAND OF  THE VEHICLE
        return this.Brand;
    }
    public int No_doors(){
        return this.no_doors;//GIVE INFORMATION ABOUT NUMBER OF DOORS IN THE VEHICLE
    }
    public String getColor(){ //GIVE INFORMATION ABOUT THE COLOUR OF THE VEHICLE
        return color;
    }
    public int ID_plate(){ //GIVE INFORMATION ABOUT  ID NUMBER OF  THE VEHICLE
        return this.IDplate;
    }
    public String what_datetime_enter(){
        return "Date and time :-" + Date_time.getdate_time(date_time_enterVan);//GIVE INFORMATION ABOUT THE ENTRY DATE/TIME
    }
    public String toString(){
        return "The Vehicle : " + type_vehicle() + " ID number : " + this.IDplate + " of color- " + this.color + " with " + this.no_doors + " number of doors and " + this.no_seats + "number of seats " + " Entered Vehicle park at : " + what_datetime_enter();
    }
}


class Threewheeler extends Vehicle{
    public int no_seats;
    public boolean isTaxi;
    public Date date_time_enterThreewheel;
    String Brand = "Threewheeler";
    Threewheeler(String color, int IDplate, int no_seats, boolean isTaxi){
        this.color = color;
        this.no_seats = no_seats;
        this.isTaxi = isTaxi;
        this.IDplate = IDplate;
        date_time_enterThreewheel = new Date();
    }

    public  boolean IsTaxi(){   //GIVE INFORMATION ABOUT WHETHER THE THREEWHEELER IS TAXI OR NOT
        return  isTaxi;
    }
    public String getColor(){  //GIVE INFORMATION ABOUT THE COLOUR OF THE VEHICLE
        return color;
    }
    public String what_datetime_enter(){
       return "Date and time :-" + Date_time.getdate_time(date_time_enterThreewheel);//GIVE INFORMATION ABOUT THE ENTRY DATE/TIME
    }
    public String type_vehicle(){  //GIVE INFORMATION ABOUT  BRAND OF  THE VEHICLE
        return this.Brand;
    }
    public int ID_plate(){  //GIVE INFORMATION ABOUT  ID NUMBER OF  THE VEHICLE
        return this.IDplate;
    }
    public String toString(){
        return "The Vehicle : "+ type_vehicle() + " ID number : " + this.IDplate + " of color- " + this.color + " with "  + this.no_seats + "number of seats " + " is a TAXI : " + this.IsTaxi() +  " Entered Vehicle park at : " + what_datetime_enter() ;
    }
}

class Date_time{
    static DateFormat date_time_format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    //RETURN THE DATE & TIME OF THE OBJECT CREATION
    public static String getdate_time(Date date_time_obj_created){  
        return "" + date_time_format.format(date_time_obj_created);
    }
}

interface CarParkManager{
    final int totalVehicleSlots = 20;
    public boolean VehicleEntered(Vehicle vehicle);
    public int VehicleLeft(int IDnum);
    public int getNumEmptySlots();
    public int getNumOccupiedSlots();
}

class SLIITCarParkManager implements CarParkManager{  
    ArrayList<Vehicle> vehicle_parked = new ArrayList<Vehicle>(totalVehicleSlots);
    public boolean VehicleEntered(Vehicle vehicle){
        boolean a;
        a = isfreeslot_available();
        if(a){   //IF THERE ARE FREE SLOTS AVAILABLE, THEN THE ENTERED VEHICLE WILL BE ALLOWED TO PARK 
            vehicle_parked.add(vehicle);
            System.out.println("" + vehicle.toString() + " is parked in the vehicle park ");
            System.out.println("Available free slots : " + getNumEmptySlots());
            return true;
        }
        else{
            System.out.println("No parking slots available"); //IF THERE ARE NO FREE SLOTS AVAILABLE, THEN THE ENTERED VEHICLE WILL NOT BE ALLOWED TO PARK 
            return false;
        }
    }
    public int getNumOccupiedSlots(){  //RETURN THE NUMBER OF OCCUPIED SLOTS IN VEHICLE PARK
        return vehicle_parked.size();
    }

    public int getNumEmptySlots(){   //RETURN THE NUMBER OF FREE SLOTS IN VEHICLE PARK
        return totalVehicleSlots-vehicle_parked.size();
    }
     
    public  boolean isfreeslot_available(){ //THIS METHOD WILL CHECK WHETHER THERE ARE FREE SLOTS AVAILABLE IN THE VEHICLE PARK OR NOT AND NOTIFY IT 
        if(getNumEmptySlots()>0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean Is_the_process_successful(Vehicle vehicle){ //RETURNS TRUE IF THE PROCESS IS SUCCESSFULL AND THE VEHICLE IS PARKED & IF NOT, IT RETURNS FALSE
        if(vehicle_parked.contains(vehicle)){
            return true;
        }
        else{
            return false;
        }
    }
    public int VehicleLeft(int IDnum){ //IF A VEHICLE IS LEAVING THE PARK, IT WILL BE REMOVED FROM THE ARRAY LIST AND PRINT THE INFORMATION ABOUT THE LEAVING VEHICLE AND RETURN THE NUMBER OF FREE SLOTS AVAILABLE
        for(int i=0; i<vehicle_parked.size(); i++){
            if(vehicle_parked.get(i).IDplate==IDnum){
                System.out.println("The Vehicle: "+ vehicle_parked.get(i).type_vehicle() + " ID number : " + vehicle_parked.get(i).IDplate + " is leaving the park");
            }
        }
        vehicle_parked.removeIf(n -> (n.ID_plate()==IDnum));// IT GOES THROUGH THE ARRAY LIST AND REMOVE THE OBJECT THAT SATISFIES THIS CONDITION
        return getNumEmptySlots();
    }

    public void Print_Parked_Vehicles(){ //IT PRINTS THE LIST OF INFORMATION ABOUT THE VEHICLES PARKED IN THE VEHICLE PARK
        System.out.println("Number of Vehicles Parked in the vehicle park: " + vehicle_parked.size());
        System.out.println();
        for(int i=0; i<vehicle_parked.size(); i++){
            System.out.println("The Vehicle : "+ vehicle_parked.get(i).type_vehicle() + " ID number : " + vehicle_parked.get(i).IDplate + " Entered Vehicle park at : " + vehicle_parked.get(i).what_datetime_enter());
            System.out.println();
            Vehicle.Parked_Vehicle_EntryData.add(vehicle_parked.get(i).toString());//IF A VEHICLE IS ALLOWED INTO THE PARKING LOCATION IT ADDS AN ENTRY TO THE VEHICLE CLASS 
        }
    }
}


public class Park{
    public static void main(String[] args){
        Car[] car = new Car[20];
        Van[] van = new Van[20];
        Threewheeler[] threewheeler = new Threewheeler[20];
        int i = 1;
        int j = 1;
        int k = 1;
        SLIITCarParkManager SLIITCarParkManager1 = new SLIITCarParkManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("If your vehicle is \n 1)Car press-1 \n 2)Van press-2 \n 3)Threewheel press-3 \n 4)to Leave the PARK press-4 \n 5) to view the details of parked vehicles press-5 \n 6) to EXIT type-exit");
        String input = scanner.next();
        while(!(input.equals("exit"))){
            if(input.equals("1")){
                System.out.println("Enter the number of doors in your car");
                int a = scanner.nextInt();
                System.out.println("Enter you car color");
                String b = scanner.next();
                System.out.println("Enter you car ID number");
                int c = scanner.nextInt();
                if(i<20){
                    car[i] = new Car(a, b, c);
                    System.out.println("The Processing completed successfully :- " + SLIITCarParkManager1.VehicleEntered(car[i]));
                    if(SLIITCarParkManager1.Is_the_process_successful(car[i])){
                        System.out.println("your token number is car" + i );//PRINT THE TOKEN NUMBER OF THE CAR PARKED IN THE VEHICLE PARK
                    }
                }
                i++;
                System.out.println("WAITING FOR THE NEXT VEHICLE");
                System.out.println("If your vehicle is \n 1)Car press-1 \n 2)Van press-2 \n 3)Threewheel press-3 \n 4)to Leave the PARK press-4 \n 5) to view the details of parked vehicles press-5 \n 6) to EXIT type-exit");
                input = scanner.next();
            }else if(input.equals("2")){
                System.out.println("Enter the number of doors in your van");
                int a = scanner.nextInt();
                System.out.println("Enter you van color");
                String b = scanner.next();
                System.out.println("Enter you van ID number");
                int c = scanner.nextInt();
                System.out.println("Enter the number of seats in your van");
                int d = scanner.nextInt();
                if(j<20){
                    van[j] = new Van(a, b, c, d);
                    System.out.println("The Processing completed successfully :- " + SLIITCarParkManager1.VehicleEntered(van[j]));
                    if(SLIITCarParkManager1.Is_the_process_successful(van[j])){
                        System.out.println("your token number is van" + j );//PRINT THE TOKEN NUMBER OF THE VAN PARKED IN THE VEHICLE PARK
                    }
                }
                j++;
                System.out.println("WAITING FOR THE NEXT VEHICLE");
                System.out.println("If your vehicle is \n 1)Car press-1 \n 2)Van press-2 \n 3)Threewheel press-3 \n 4)to Leave the PARK press-4 \n 5) to view the details of parked vehicles press-5 \n 6) to EXIT type-exit");
                input = scanner.next();
            }
            else if(input.equals("3")){
                System.out.println("Enter you threewheeler color");
                String a = scanner.next();
                System.out.println("Enter you threewheeler ID number");
                int b = scanner.nextInt();
                System.out.println("Enter the number of seats in your threewheeler");
                int c = scanner.nextInt();
                System.out.println("Is your threewheeler a taxi(true/false):");
                boolean d = scanner.nextBoolean();
                if(k<20){
                    threewheeler[k] = new Threewheeler(a, b, c, d);
                    System.out.println("The Processing completed successfully :- " + SLIITCarParkManager1.VehicleEntered(threewheeler[k]));
                    if(SLIITCarParkManager1.Is_the_process_successful(threewheeler[k])){
                        System.out.println("your token number is threewheeler" + k );//PRINT THE TOKEN NUMBER OF THE THREEWHEEL PARKED IN THE VEHICLE PARK
                    }
                }
                k++;
                System.out.println("WAITING FOR THE NEXT VEHICLE");
                System.out.println("If your vehicle is \n 1)Car press-1 \n 2)Van press-2 \n 3)Threewheel press-3 \n 4)to Leave the PARK press-4 \n 5) to view the details of parked vehicles press-5 \n 6) to EXIT type-exit");
                input = scanner.next();
            }
            //IF A VEHICLE WANTS TO LEAVE THE PARK IT CAN BE DONE USING THIS CODE BELOW
            else if(input.equals("4")){ 
                System.out.println("enter your Vehicle ID number");
                int IDnum = scanner.nextInt();  //THE VEHICLE ID NUMBER  SHOULD BE GIVEN AS INPUT
                System.out.println("Free slots available:- " + SLIITCarParkManager1.VehicleLeft(IDnum)); //THIS WILL REMOVE THE CORROSBONDING VEHICLE OBJECT IN THE ARRAY LIST USING THE ID NUMBER PROVIDED 
                System.out.println();
                System.out.println("If your vehicle is \n 1)Car press-1 \n 2)Van press-2 \n 3)Threewheel press-3 \n 4)to Leave the PARK press-4 \n 5) to view the details of parked vehicles press-5 \n 6) to EXIT type-exit");
                input = scanner.next();
            }
            //IT PRINTS THE PARKED VEHICLES WHEN THE USER SELECT THIS OPTION
            else if(input.equals("5")){              
                SLIITCarParkManager1.Print_Parked_Vehicles();
                System.out.println("If your vehicle is \n 1)Car press-1 \n 2)Van press-2 \n 3)Threewheel press-3 \n 4)to Leave the PARK press-4 \n 5) to view the details of parked vehicles press-5 \n 6) to EXIT type-exit");
                input = scanner.next();
            }
            else{
                System.out.println("Invalid input try again");//IF AN INVALID INPUT IS GIVEN BY THE USER THEN THE PROGRAMME NOTIFIES THE USER & GIVE OPTION TO TRY AGAIN
                System.out.println("Would u like to try again:  press Y if Yes or press any key if No");
                String try_again = scanner.next();
                if(try_again.equals("Y")){
                    System.out.println();
                    System.out.println("If your vehicle is \n 1)Car press-1 \n 2)Van press-2 \n 3)Threewheel press-3 \n 4)to Leave the PARK press-4 \n 5) to view the details of parked vehicles press-5 \n 6) to EXIT type-exit");
                    input = scanner.next();
                }
                else{
                    break;
                }
            }  
        } 
        // IT PRINTS THE ENTRY DATA STORED IN THE VEHICLE CLASS ABOUT THE VEHICLES ALLOWED TO PARK 
        //System.out.println(Vehicle.Parked_Vehicle_EntryData);
        System.out.println("THIS PROGRAMME  IS DESIGNED BY M.SARANYAN");//EN19382722
        
    }
} 
