import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Address
{
    String street,country,addressType;
    long pin_code;
    Address(String street,String country,long pin_code,String addressType)
    {
        this.country=country;
        this.street=street;
        this.pin_code=pin_code;
        this.addressType=addressType;
    }

    void displayAddress()
    {
        System.out.println("country =  "+country );
        System.out.println("street = "+street );
        System.out.println("pin_code = " +pin_code );
        System.out.println("addressType = "+addressType);
    }

}

class Person
{
    String name,place,email;
    ArrayList<Address> address;
    Person(String name,String place,String email,ArrayList<Address> address)
    {
        this.name=name;
        this.place=place;
        this.email=email;
        this.address=address;
    }

    void displayPerson()
    {
        System.out.println("name =  "+this.name );
        System.out.println("place = "+this.place );
        System.out.println("email = " +this.email );
    }

}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        JSONParser parser = new JSONParser();
        ArrayList<Person> ans=new ArrayList<>();
        try {

            Scanner sc = new Scanner(new File("sample.json"));
            String jsonString = sc.useDelimiter("\\A").next();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);

            for(int i=0;i<jsonArray.size();i++)
            {
                JSONObject ob=(JSONObject)jsonArray.get(i);

                JSONArray addrArr=(JSONArray)ob.get("address");
                ArrayList<Address> address=new ArrayList<>();
                for(int j=0;j<addrArr.size();j++)
                {
                    JSONObject addr=(JSONObject)addrArr.get(j);
                    Address add=new Address((String) addr.get("street"), (String) addr.get("country"), (Long) addr.get("pin_code"),(String) addr.get("addressType"));
                    address.add(add);
                }

                Person person=new Person((String) ob.get("name"), (String) ob.get("place"), (String) ob.get("email"),address);

                ans.add(person);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for(int i=0;i<ans.size();i++)
        {
            Person p=ans.get(i);
            System.out.println("Person");
            p.displayPerson();
            System.out.println("Address");
            for(Address j:p.address) {
                j.displayAddress();
                System.out.println();
            }

        }

    }

}
