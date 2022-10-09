package A2;
// product id to be handled

import java.util.*;

class Category {
    private String name;
    static private ArrayList<Category> categories = new ArrayList<Category>();

    private ArrayList<Product> products = new ArrayList<Product>();

    static private ArrayList IDs = new ArrayList();

    void setName(String s) {
        this.name = s;
    }

    String getName() {
        return this.name;
    }

    int getProdListSize() {
        return products.size();
    }

    Product getProd(int i) {
        return products.get(i);
    }

    void getDetails(int a) {
        System.out.println("Name: " + products.get(a).getProdName() + "\n" +
                "Product ID: " + products.get(a).getProdID() + "\n" +
                "Price: " + products.get(a).getPrice() + "\n"
        );
        for (int i = 0; i < products.get(a).getSpecSize(); i++) {
            System.out.println(products.get(a).getSpec(i));
        }
    }

    void addProdToCat(Product P) {
        this.products.add(P);
    }
}


class Admin {
    static private ArrayList<Category> categories = new ArrayList<Category>();
    static private ArrayList<Customer> customerList = new ArrayList<Customer>();
    static private String password = "2021486";
    static private String uName = "saksham";

    static Scanner sc = new Scanner(System.in);

    String getPassword() {
        return password;
    }

    String getuName() {
        return uName;
    }


    int getCatSize(){
        return categories.size();
    }

    Category getCat(int i){
        return categories.get(i);
    }
    void showProducts() {
        for (Category i : categories) {
            System.out.println("\n->" + i.getName() + "\n");
            for (int j = 0; j < i.getProdListSize(); j++) {
                System.out.println((j + 1) + ") ");
                i.getDetails(j);
            }
        }
    }

    void addCategory() {
        System.out.println("Enter name of the category: ");
        String s = sc.nextLine();
        Category C = new Category();
        C.setName(s);
        categories.add(C);
        System.out.println("Category Added!\n");
        System.out.println("Enter Name of the product: ");
        s = sc.nextLine();
        System.out.println("Enter The Product ID: ");
        String id= sc.nextLine();
        if(!checkID(id)){
            System.out.println("Please try again and enter a unique id!\n");
            return;
        }
        System.out.println("Enter Price: ");
        float p = sc.nextFloat();
        System.out.println("Enter Quantity: ");
        int q = sc.nextInt();
        Product P = new Product();
        P.setPrice(p);
        P.setProdName(s);
        P.setProdID(id);
        P.setCategory(categories.size() + 1);
        System.out.println("How many Specifications you want to add for this product? ");
        int x = sc.nextInt();
        for (int j = 0; j < x; j++) {
            String in = sc.nextLine();
            P.addSpec(in);
        }
        C.addProdToCat(P);
        System.out.println("Product Added!\n");

    }

    void delCategory() {
        System.out.println("Enter name of the category: ");
        String s = sc.nextLine();
        for (int i = 0; i < categories.size(); i++) {
            if (s.compareTo(categories.get(i).getName()) == 0) {
                Category C = categories.get(i);
                for (int j = 0; j < C.getProdListSize(); j++) {
                    C.getProd(j).delete();
                }
                categories.remove(i);
            }
        }
        System.out.println("Category Removed!\n");
    }

    boolean checkID(String id){
        for(int i=0;i<categories.size();i++){
            for(int j=0;j<categories.get(i).getProdListSize();j++){
                if(id.compareTo(categories.get(i).getProd(j).getProdID())==0){
                    return false;
                }
            }
        }
        return true;
    }

    void addProduct() {
        System.out.println("Enter The Category ID: ");
        int n = sc.nextInt();
        for (int i = 0; i < categories.size(); i++) {
            if (i + 1 == n) {
                Category C = categories.get(i);
                System.out.println("Enter Name of the product: ");
                String s = sc.nextLine();
                System.out.println("Enter The Product ID: ");
                String id= sc.nextLine();
                if(!checkID(id)){
                    System.out.println("Please try again and enter a unique id!\n");
                    return;
                }  // to check uniqueness of id
                System.out.println("Enter Price: ");
                float p = sc.nextFloat();
                System.out.println("Enter Quantity: ");
                int q = sc.nextInt();
                Product P = new Product();
                P.setPrice(p);
                P.setProdName(s);
                P.setProdID(id);
                P.setCategory(i + 1);
                System.out.println("How many Specifications you want to add for this product? ");
                int x = sc.nextInt();
                for (int j = 0; j < x; j++) {
                    String in = sc.nextLine();
                    P.addSpec(in);
                }
                C.addProdToCat(P);
                System.out.println("Product Added!\n");
            }
        }
    }

    void delProduct() {
        System.out.println("Enter category ID: ");
        int n = sc.nextInt();
        for (int i = 0; i < categories.size(); i++) {
            if (i + 1 == n) {
                Category C = categories.get(i);
                System.out.println("Products: \n");
                for (int j = 0; j < C.getProdListSize(); j++) {
                    System.out.println((j + 1) + ") " + C.getProd(i).getProdName() + "\n");
                }
                int x = sc.nextInt();
                for (int j = 0; j < C.getProdListSize(); j++) {
                    if (j == x - 1) {
                        C.getProd(j).delete();
                    }
                }
                System.out.println("Product Deleted!");
            }
        }
    }

    void setDiscount() {
        System.out.println("Enter category ID: ");
        int n = sc.nextInt();
        for (int i = 0; i < categories.size(); i++) {
            if (i + 1 == n) {
                Category C = categories.get(i);
                System.out.println("Products: \n");
                for (int j = 0; j < C.getProdListSize(); j++) {
                    System.out.println((j + 1) + ") " + C.getProd(i).getProdName() + "\n");
                }
                System.out.println("Enter Serial no.: ");
                int x = sc.nextInt();
                for (int j = 0; j < C.getProdListSize(); j++) {
                    if (j == x - 1) {
                        System.out.println("Enter Elite discount(%): ");
                        int e = sc.nextInt();
                        System.out.println("Enter Prime discount(%): ");
                        int p = sc.nextInt();
                        System.out.println("Enter Discount(%): ");
                        int d = sc.nextInt();
                        C.getProd(j).setDiscount(d);
                        C.getProd(j).setPrimeDiscount(p);
                        C.getProd(j).setEliteDiscount(e);
                    }
                }
            }
        }
    }

    void addGiveaway() {
        System.out.println("\nEnter Product ID of the First Product: ");
        String a=sc.nextLine();
        System.out.println("\nEnter Product ID of the Second Product: ");
        String b=sc.nextLine();
        System.out.println("Enter Combined Price: ");
        float p = sc.nextFloat();
        float p1=0,p2=0;
        int flag1=0, flag2=0;
        for(int i=0;i<categories.size();i++){
            for(int j=0;j<categories.get(i).getProdListSize();j++){
                if(categories.get(i).getProd(j).getProdID().compareTo(a)==0){
                    flag1=1;
                    p1=categories.get(i).getProd(j).getPrice();
                }
                if(categories.get(i).getProd(j).getProdID().compareTo(b)==0){
                    flag2=1;
                    p2=categories.get(i).getProd(j).getPrice();
                }
            }
        }
        if(flag1==0 || flag2==0){
            System.out.println("\nOne of the products is not available!\n");
            return;
        }
        if(p1+p2<=p){
            System.out.println("Combined price cannot be less than or equal to the deal price! ");
            return;
        }
        Deals d= new Deals();
        d.setProd1(a);
        d.setProd2(b);
        d.setDealPrice(p);
        d.addToDeals();
    }

    void addToCart(Customer C) {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ") " + categories.get(i));
        }
        int n = sc.nextInt();
        Category cat = categories.get(n - 1);
        System.out.println("\n->Products: ");
        for (int i = 0; i < cat.getProdListSize(); i++) {
            System.out.println((i + 1) + ") " + cat.getProd(i) + "\n");
        }
        int x = sc.nextInt();
        System.out.println("Enter Quantity: ");
        int q=sc.nextInt();
        if(q>cat.getProd(x-1).getQuantity()){
            System.out.println("max quantity availabl is "+cat.getProd(x-1).getQuantity()+" which has been adde to your cart");
        }
        for(int i=0;i<Math.min(q,cat.getProd(x-1).getQuantity());i++){
            C.addCart(cat.getProd(x - 1));
        }
    }

    void dealsToCart(Customer C){

    }
}



class Customer {
    private ArrayList coupons = new ArrayList();
    private ArrayList<Product> cart = new ArrayList<Product>();
    private ArrayList<Deals> dealItems = new ArrayList<Deals>();
    static private ArrayList<Customer> customerList = new ArrayList<Customer>();
    private String name = "";
    private String password = "";

    private int status = 0; //0->normal 1->prime 2->elite

    private String phoneNo, emailID;
    private int age;

    private float cartAmount = 0;

    private float walletAmount = 1000;
    private float dealAmount=0;

    private double discount=0;
    static Scanner sc = new Scanner(System.in);

    float getWalletAmount() {
        return this.walletAmount;
    }

    String getuName() {
        return this.name;
    }


    void setuName(String s) {
        this.name = s;
    }

    void setAge(int a) {
        this.age = a;
    }

    void setPhoneNo(String s) {
        this.phoneNo = s;
    }

    void setEmailID(String s) {
        this.emailID = s;
    }

    void setPassword(String s) {
        this.password = s;
    }

    void addCust(String n, String ph, String e, int a, String p) {
        this.name = n;
        this.phoneNo = ph;
        this.password = p;
        this.age = a;
        this.emailID = e;
        customerList.add(this);
    }

    int getCustSize() {
        return customerList.size();
    }

    String getPhoneNo() {
        return this.phoneNo;
    }

    String getEmailID() {
        return this.emailID;
    }

    Customer getCustomer(int i) {
        return customerList.get(i);
    }

    String getPassword() {
        return this.password;
    }

    void addCart(Product P) {
        this.cart.add(P);
        this.cartAmount += P.getPrice();
    }

    void viewCart() {
        int j = 1;
        for (Product p : cart) {
            System.out.print(j + ") ");
            System.out.println("Name: " + p.getProdName() + "\n");
            System.out.println("Price: " + p.getPrice() + "\n");
            System.out.println("Prod ID: " + p.getCategory() + "." + p.getProdID() + "\n");
            for (int i = 0; i < p.getSpecSize(); i++) {
                System.out.println(p.getSpec(i));
            }
            j++;
        }
    }

    void emptyCart() {
        cart.clear();
    }

    void addWallet(int i) {
        this.walletAmount += i;
    }

    void upgradeStatus() {
        if (this.status == 0) {
            System.out.println("\nCurrent Status: Normal");
        } else if (this.status == 1) {
            System.out.println("\nCurrent Status: Prime");
        } else {
            System.out.println("\nCurrent Status: Elite");
        }
        System.out.println("\nChoose new status: ");
        String s = sc.nextLine();
        if(s.charAt(0)=='p' || s.charAt(0)=='P'){
            if(this.status==1){
                System.out.println("\nYou're Already Prime member!\n");
            }
            else{
                this.status=1;
                this.walletAmount-=200;
                this.discount=0.05;
                System.out.println("\nStatus updated to Prime!\n");
            }
        }
        else if(s.charAt(0)=='e' || s.charAt(0)=='E'){
            if(this.status==2){
                System.out.println("\nYou're Already Elite member!\n");
            }
            else{
                this.status=2;
                this.walletAmount-=300;
                this.discount=0.1;
                System.out.println("\nStatus updated to Elite!\n");
            }
        }
    }
    void addToCartDeals(Deals d){
        this.dealItems.add(d);
        this.dealAmount+=d.getDealPrice();
    }

    void checkOutCart(){
        int f=0;
        double cartValue=0;
        double min;
        for(Product i: this.cart){
            if(i.getExists()==0){
                this.cart.remove(i);
            }
        }
        Admin ad = new Admin();
        for(Deals d: this.dealItems){
            for(int i=0;i<ad.getCatSize();i++){
                for(int j=0;j<ad.getCat(i).getProdListSize();j++){
                    if(d.getProd1().compareTo(ad.getCat(i).getProd(j).getProdID())==0){
                        if(ad.getCat(i).getProd(j).getExists()==0){
                            this.dealItems.remove(d);
                        }
                    }
                    if(d.getProd2().compareTo(ad.getCat(i).getProd(j).getProdID())==0){
                        if(ad.getCat(i).getProd(j).getExists()==0){
                            this.dealItems.remove(d);
                        }
                    }
                }
            }
        }
        for(Product i:this.cart){
            min=1000000000;
            if(i.getPrice()-this.discount*i.getPrice()<min){
                min=i.getPrice()-this.discount*i.getPrice();
            }
            if(i.getPrice()-(i.getDiscount()*i.getPrice())/100<min){
                min=i.getPrice()-(i.getDiscount()*i.getPrice())/100;
            }
            if(coupons.size()!=0){
                Collections.sort(coupons);
                if(i.getPrice()-((double)coupons.get(coupons.size()-1)*i.getPrice())/100<min){
                    min=i.getPrice()-((double)coupons.get(coupons.size()-1)*i.getPrice())/100;
                    f=1;
                }
            }
            cartValue+=min;
        }
        if(f==1){
            coupons.remove(coupons.get(coupons.size()-1));
        }
        for(Deals i: this.dealItems){
            cartValue+=i.getDealPrice();
        }
        float delivery=100;

        if(cartValue>5000){
            int num=0;
            if(this.status==2){
                num=(int)(Math.random() * (5 - 3 + 1) + 3);
            }
            if(this.status==1){
                num=(int)(Math.random() * (3 - 1 + 1) + 1);
            }
            for(int i=0;i<num;i++){
                int coupVal=(int)(Math.random() * (16 - 5 + 1) + 5);
                coupons.add(coupVal);
            }
        }

        if(this.status==1){
            delivery+=cartValue*0.02;
        }
        else if(this.status==0){
            delivery+=cartValue*0.05;
        }
        cartValue+=delivery;
        if(cartValue>this.walletAmount){
            System.out.println("\n---Insufficient Balance!!---\n");
            return;
        }
        System.out.println("\nYour cart: \n");
        for(Product i: this.cart){
            System.out.println("\nName: "+i.getProdName()+"\n"+
                    "Price: "+i.getPrice()+"\n");
        }
        System.out.println("Your total Bill value after discounts: "+ cartValue+" (This includes "+delivery+" fees)");
        this.walletAmount-=cartValue;
    }

}



class Product extends Category {
    static private ArrayList<String> specifications = new ArrayList<String>();
    private String prodName;
    private String prodID;
    private float price;
    private int category;
    private int quantity;
    private int eliteDiscount = 0;
    private int primeDiscount = 0;
    private int discount = 0;

    int exists = 1;

    void setProdID(String p) {
        this.prodID=p;
    }

    void setEliteDiscount(int i) {
        this.eliteDiscount = i;
    }

    int getELiteDiscount() {
        return this.eliteDiscount;
    }

    void setPrimeDiscount(int i) {
        this.primeDiscount = i;
    }

    int getPrimeDiscount() {
        return this.primeDiscount;
    }

    void setDiscount(int i) {
        this.discount = i;
    }

    int getDiscount() {
        return this.discount;
    }

    void setPrice(float p) {
        this.price = p;
    }

    void setCategory(int c) {
        this.category = c;
    }

    void setQuantity(int q) {
        this.quantity = q;
    }

    String getProdID() {
        return this.prodID;
    }

    float getPrice() {
        return this.price;
    }

    int getCategory() {
        return this.category;
    }

    int getQuantity() {
        return this.quantity;
    }

    void addSpec(String s) {
        specifications.add(s);
    }

    void setProdName(String n) {
        this.prodName = n;
    }

    String getProdName() {
        return this.prodName;
    }

    String getSpec(int i) {
        return specifications.get(i);
    }

    int getExists() {
        return this.exists;
    }

    int getSpecSize() {
        return specifications.size();
    }

    void delete() {
        exists = 0;
    }
}


class Deals extends Product {
    static private ArrayList<Deals> deals = new ArrayList<Deals>();
    private String prod1;
    private String prod2;
    private float dealPrice;

    void setProd1(String s){
        this.prod1=s;
    }
    void setProd2(String s){
        this.prod2=s;
    }
    String getProd1(){
        return this.prod1;
    }
    String getProd2(){
        return this.prod2;
    }

    void setDealPrice(float p){
        this.dealPrice=p;
    }
    float getDealPrice(){
        return this.dealPrice;
    }

    void addToDeals(){
        deals.add(this);
    }
}


public class main{
    static Scanner sc= new Scanner(System.in);
    static Customer c=new Customer();
    static void admin(){
        Admin ad= new Admin();
        String p,u;
        System.out.println("ENTER USERNAME: ");
        u=sc.nextLine();
        System.out.println("ENTER PASSWORD: ");
        p=sc.nextLine();
        if(u.compareTo(ad.getuName())==0 && p.compareTo(ad.getPassword())==0) {
            while(true){
                System.out.println("\nWelcome " + u + "!\n");
                System.out.println("\nPlease choose any one of the following actions:\n" +
                        "1) Add category\n" +
                        "2) Delete category\n" +
                        "3) Add Product\n" +
                        "4) Delete Product\n" +
                        "5) Set Discount on Product\n" +
                        "6) Add giveaway deal\n" +
                        "7) Back\n");
                int n = sc.nextInt();
                if (n == 1){
                    ad.addCategory();
                }
                else if(n==2){
                    ad.delCategory();
                }
                else if(n==3){
                    ad.addProduct();
                }
                else if(n==4){
                    ad.delProduct();
                }
                else if(n==5){
                    ad.setDiscount();
                }
                else if(n==6){
                    ad.addGiveaway();
                }
                else if(n==7){
                    return;
                }
            }
        }
    }

    static void prodCatalog(){

    }

    static void customerMenu(Customer C){
        Admin A= new Admin();
        while(true){
            System.out.println("Welcome "+C.getuName()+"!!\n" +
                    "1) browse products\n" +
                    "2) browse deals\n" +
                    "3) add a product to cart\n" +
                    "4) add products in deal to cart\n" +
                    "5) view coupons\n" +
                    "6) check account balance\n" +
                    "7) view cart\n" +
                    "8) empty cart\n" +
                    "9) checkout cart\n" +
                    "10) upgrade customer status\n" +
                    "11) Add amount to wallet\n" +
                    "12) back\n");
            int n = sc.nextInt();
            if(n==1){
                A.showProducts();
            }
            else if(n==2){
                //show deals
            }
            else if(n==3){
                A.addToCart(C);
            }
            else if(n==4){
                A.dealsToCart(C);
            }
            else if(n==5){
                //coupons
            }
            else if(n==6){
                System.out.println("Current Account Balance = "+C.getWalletAmount()+"\n");
            }
            else if(n==7){
                C.viewCart();
            }
            else if(n==8){
                C.emptyCart();
            }
            else if(n==9){
                C.checkOutCart();
            }
            else if(n==10) {
                //upgrade status
                C.upgradeStatus();
            }
            else if(n==11){
                System.out.println("\nEnter the amount you want add: ");
                int x=sc.nextInt();
                C.addWallet(x);
            }
            else if(n==12){
                return;
            }
        }
    }

    static void signUp(){
        String n,p,e,ph;
        int a;
        System.out.println("Enter User name: ");
        n=sc.nextLine();
        System.out.println("Enter Phone no.: ");
        ph= sc.nextLine();
        System.out.println("Enter email ID: ");
        e=sc.nextLine();
        System.out.println("Enter Age: ");
        a=sc.nextInt();
        System.out.println("Enter Password: ");
        p=sc.nextLine();
        Customer C = new Customer();
        for(int i =0;i<C.getCustSize();i++){
            if(C.getCustomer(i).getEmailID().compareTo(e)==0 || C.getCustomer(i).getPhoneNo().compareTo(ph)==0){
                System.out.println("User Already Exists!\n");
                return;
            }
        }
        C.addCust(n,ph,e,a,p);
        System.out.println("Thank You for Registering on FLIPZON!\n");
    }

    static void logIn(){
        String n,p;
        int f=0;
        System.out.println("Enter User name: ");
        n=sc.nextLine();
        System.out.println("Enter Password: ");
        p=sc.nextLine();
        Customer C= new Customer();
        for(int i=0;i<C.getCustSize();i++){
            if(n.compareTo(C.getCustomer(i).getuName())==0 && p.compareTo(C.getCustomer(i).getPassword())==0){
                f=1;
                customerMenu(C.getCustomer(i));
            }
        }
        if(f==0)
            System.out.println("Wrong Credentials! Returning Back\n");
    }
    static void customer(){
        while(true){
            System.out.println("\n1) Sign up\n" +
                    "2) Log in\n" +
                    "3) Back");
            int n=sc.nextInt();
            if(n==1){
                signUp();
            }
            else if(n==2){
                logIn();
            }
            else{
                return;
            }
        }
    }

    static void availableDeals(){

    }

    static void mainMenu(){
        while(true) {
            System.out.println("\nWELCOME TO FLIPZON\n" +
                    "1) Enter as Admin\n" +
                    "2) Explore the Product Catalog\n" +
                    "3) Show Available Deals\n" +
                    "4) Enter as Customer\n" +
                    "5) Exit the Application\n");
            int n;
            n = sc.nextInt();
            if (n == 1) {
                admin();
            } else if (n == 2) {
                prodCatalog();
            } else if (n == 3) {
                availableDeals();
            } else if (n == 4) {
                customer();
            } else if (n == 5) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        String s;
        s= sc.nextLine();
        if(s.compareTo("enter")==0){
            mainMenu();
        }
    }
}
