// product id to be handled




import java.util.*;

class Product{
    static private int pid=1;
    static private ArrayList<String> specifications = new ArrayList<String>();
    private String prodName;
    private int prodID;
    private int price;
    private int category;
    private int quantity;
    private int eliteDiscount=0;
    private int primeDiscount=0;
    private int discount=0;

    int exists=1;

    void setProdID(){
        this.prodID=pid;
        pid++;
    }
    void setEliteDiscount(int i){
        this.eliteDiscount=i;
    }
    int getELiteDiscount(){
        return this.eliteDiscount;
    }
    void setPrimeDiscount(int i){
        this.primeDiscount=i;
    }
    int getPrimeDiscount(){
        return this.primeDiscount;
    }
    void setDiscount(int i){
        this.discount=i;
    }
    int getDiscount(){
        return this.discount;
    }

    void setPrice(int p){
        this.price=p;
    }

    void setCategory(int c){
        this.category=c;
    }

    void setQuantity(int q){
        this.quantity=q;
    }

    int getProdID(){
        return this.prodID;
    }
    int getPrice(){
        return this.price;
    }
    int getCategory(){
        return this.category;
    }
    int getQuantity(){
        return this.quantity;
    }

    void addSpec(String s){
        specifications.add(s);
    }
    void setProdName(String n){
        this.prodName=n;
    }

    String getProdName(){
        return this.prodName;
    }

    String getSpec(int i){
        return specifications.get(i);
    }

    int getExists(){
        return this.exists;
    }
    int getSpecSize(){
        return specifications.size();
    }
    void delete(){
        exists=0;
    }
}




class Category {
    private String name;

    private ArrayList<Product> products = new ArrayList<Product>();

    static private ArrayList IDs = new ArrayList();
    void setName(String s){
        this.name=s;
    }

    String getName(){
        return this.name;
    }

    int getProdListSize(){
        return products.size();
    }

    Product getProd(int i){
        return products.get(i);
    }

    void getDetails(int a){
        System.out.println("Name: "+products.get(a).getProdName()+"\n"+
                "Product ID: "+ products.get(a).getProdID()+"\n"+
                "Price: " + products.get(a).getPrice()+"\n"
                );
        for(int i=0;i<products.get(a).getSpecSize();i++){
            System.out.println(products.get(a).getSpec(i));
        }
    }

    void addProdToCat(Product P){
        this.products.add(P);
    }
}

class Admin{
    static private ArrayList<Category> categories= new ArrayList<Category>();
    static private ArrayList<Customer> customerList = new ArrayList<Customer>();
    static private String password="2021486";
    static private String uName="saksham";

    static Scanner sc = new Scanner(System.in);
    String getPassword(){
        return password;
    }

    String getuName(){
        return uName;
    }


    void showProducts(){
        for(Category i: categories){
            System.out.println("\n->"+i.getName()+"\n");
            for(int j=0;j<i.getProdListSize();j++){
                System.out.println((j+1)+") ");
                i.getDetails(j);
            }
        }
    }
    void addCategory(){
        System.out.println("Enter name of the category: ");
        String s= sc.nextLine();
        Category C= new Category();
        C.setName(s);
        categories.add(C);
        System.out.println("Category Added!\n");
        System.out.println("Enter Name of the product: ");
        s = sc.nextLine();
        System.out.println("Enter Price: ");
        int p=sc.nextInt();
        System.out.println("Enter Quantity: ");
        int q=sc.nextInt();
        Product P= new Product();
        P.setPrice(p);
        P.setProdName(s);
        P.setProdID();
        P.setCategory(categories.size()+1);
        System.out.println("How many Specifications you want to add for this product? ");
        int x= sc.nextInt();
        for(int j=0;j<x;j++){
            String in=sc.nextLine();
            P.addSpec(in);
        }
        C.addProdToCat(P);
        System.out.println("Product Added!\n");

    }

    void delCategory(){
        System.out.println("Enter name of the category: ");
        String s= sc.nextLine();
        for(int i =0;i< categories.size();i++){
            if(s.compareTo(categories.get(i).getName())==0){
                Category C=categories.get(i);
                for(int j=0;j<C.getProdListSize();j++){
                    C.getProd(j).delete();
                }
                categories.remove(i);
            }
        }
        System.out.println("Category Removed!\n");
    }

    void addProduct(){
        System.out.println("Enter The Category ID: ");
        int n=sc.nextInt();
        for(int i=0;i<categories.size();i++){
            if(i+1==n){
                Category C=categories.get(i);
                System.out.println("Enter Name of the product: ");
                String s = sc.nextLine();
                System.out.println("Enter Price: ");
                int p=sc.nextInt();
                System.out.println("Enter Quantity: ");
                int q=sc.nextInt();
                Product P= new Product();
                P.setPrice(p);
                P.setProdName(s);
                P.setProdID();
                P.setCategory(i+1);
                System.out.println("How many Specifications you want to add for this product? ");
                int x= sc.nextInt();
                for(int j=0;j<x;j++){
                    String in=sc.nextLine();
                    P.addSpec(in);
                }
                C.addProdToCat(P);
                System.out.println("Product Added!\n");
            }
        }
    }

    void delProduct(){
        System.out.println("Enter category ID: ");
        int n=sc.nextInt();
        for(int i=0;i<categories.size();i++){
            if(i+1==n){
                Category C=categories.get(i);
                System.out.println("Products: \n");
                for(int j=0;j<C.getProdListSize();j++){
                    System.out.println((j+1)+") "+C.getProd(i).getProdName()+"\n");
                }
                int x=sc.nextInt();
                for(int j=0;j<C.getProdListSize();j++){
                    if(j==x-1){
                        C.getProd(j).delete();
                    }
                }
                for(int j=0;j<C.getProdListSize();j++){
                    C.getProd(j).setProdID();
                }
                System.out.println("Product Deleted!");
            }
        }
    }

    void setDiscount(){
        System.out.println("Enter category ID: ");
        int n=sc.nextInt();
        for(int i=0;i<categories.size();i++){
            if(i+1==n){
                Category C=categories.get(i);
                System.out.println("Products: \n");
                for(int j=0;j<C.getProdListSize();j++){
                    System.out.println((j+1)+") "+C.getProd(i).getProdName()+"\n");
                }
                System.out.println("Enter ID: ");
                int x=sc.nextInt();
                for(int j=0;j<C.getProdListSize();j++){
                    if(j==x-1){
                        System.out.println("Enter Elite discount(%): ");
                        int e=sc.nextInt();
                        System.out.println("Enter Prime discount(%): ");
                        int p=sc.nextInt();
                        System.out.println("Enter Discount(%): ");
                        int d=sc.nextInt();
                        C.getProd(j).setDiscount(d);
                        C.getProd(j).setPrimeDiscount(p);
                        C.getProd(j).setEliteDiscount(e);
                    }
                }
            }
        }
    }

    void addGiveaway(){

    }

    void addToCart(Customer C){
        for(int i=0;i<categories.size();i++){
            System.out.println((i+1)+") "+categories.get(i));
        }
        int n=sc.nextInt();
        Category cat=categories.get(n-1);
        System.out.println("\n->Products: ");
        for(int i=0;i<cat.getProdListSize();i++){
            System.out.println((i+1)+") "+cat.getProd(i)+"\n");
        }
        int x = sc.nextInt();
        C.addCart(cat.getProd(x-1));
    }
}

class Customer{
    private ArrayList<Product> cart = new ArrayList<Product>();
    static private ArrayList<Customer> customerList= new ArrayList<Customer>();
    private String name="";
    private String password="";

    private int status=0; //0->normal 1->prime 2->elite

    private String phoneNo,emailID;
    private int age;

    private int cartAmount=0;

    private int walletAmount=0;

    void setCartAmount(int i){
        this.cartAmount=i;
    }

    int getCartAmount(){
        return this.cartAmount;
    }

    void setWalletAmount(int i){
        this.walletAmount=i;
    }

    int getWalletAmount(){
        return this.walletAmount;
    }
    String getuName(){
        return this.name;
    }


    void setuName(String s){
        this.name=s;
    }

    void setAge(int a){
         this.age=a;
    }
    void setPhoneNo(String s){
         this.phoneNo=s;
    }
    void setEmailID(String s){
         this.emailID=s;
    }
    void setPassword(String s){
        this.password=s;
    }

    void addCust(String n, String ph,String e,int a,String p){
        this.name=n;
        this.phoneNo=ph;
        this.password=p;
        this.age=a;
        this.emailID=e;
         customerList.add(this);
    }

    int getCustSize(){
        return customerList.size();
    }

    String getPhoneNo(){
        return this.phoneNo;
    }

    String getEmailID(){
         return this.emailID;
    }
    Customer getCustomer(int i){
        return customerList.get(i);
    }

    String getPassword(){
         return this.password;
    }

    void addCart(Product P){
        this.cart.add(P);
        this.cartAmount+=P.getPrice();
    }

    void viewCart(){
        int j=1;
        for(Product p: cart){
            System.out.print(j+") ");
            System.out.println("Name: "+p.getProdName()+"\n");
            System.out.println("Price: "+p.getPrice()+"\n");
            System.out.println("Prod ID: "+p.getCategory()+"."+p.getProdID()+"\n");
            for(int i=0;i<p.getSpecSize();i++){
                System.out.println(p.getSpec(i));
            }
            j++;
        }
    }

    void emptyCart(){
        cart.clear();
    }

    void addWallet(int i){
        this.walletAmount+=i;
    }
}

public class Main {
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
                // add to cart Deals
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
                //checkout cart
            }
            else if(n==10) {
                //upgrade status
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