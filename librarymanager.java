 import java.util.*;
import java.time.*;
import java.io.*;
interface libman{
    void get_data();
    void put_data();
}
class books implements libman, Serializable{
    public static Scanner sc = new Scanner(System.in);
    int book_id;
    String isbn;
    String book_name;
    String author_name;
    String publisher_name;
    int no_of_copies;
    private static final long serialVersionUID = 1L;
    books(){
        book_id = 0;
        isbn = new String();
        book_name = new String();
        author_name = new String();
        publisher_name = new String();
        no_of_copies = 0;
    }
    public void get_data(){
        System.out.println("Enter book id: ");
        book_id = sc.nextInt();
        sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?"); 
        System.out.println("Enter ISBN: ");
        isbn = sc.nextLine();
        System.out.println("Enter Book name: ");
        book_name = sc.nextLine();
        System.out.println("Enter author's name: ");
        author_name = sc.nextLine();
        System.out.println("Enter publisher's name: ");
        publisher_name = sc.nextLine();
        System.out.println("Enter the number of copies: ");
        no_of_copies =sc.nextInt();
    }
    public void put_data(){
        System.out.println("Book id: " + book_id);
        System.out.println("ISBN: " + isbn);
        System.out.println("Book name: " + book_name);
        System.out.println("Author's name: " + author_name);
        System.out.println("Publisher's name: " + publisher_name);
        System.out.println("Number of copies: " + no_of_copies);
    }
    int ret_bookid(){
        return book_id;
    }
    String ret_bookname(){
        return book_name;
    }
    void issued(){
        no_of_copies--;
    }
    void returned(){
        no_of_copies++;
    }
}
class members implements libman, Serializable{
    public static Scanner sc = new Scanner(System.in);
    int member_id;
    String member_name;
    String address;
    String email;
    String phoneno;
    int books_issued;
    int warning;
    int books_returned;
    private static final long serialVersionUID = 1L;
    members(){
        member_id = 0;
        member_name = new String();
        address = new String();
        email = new String();
        phoneno = new String();
        books_issued = 0;
        books_returned = 0;
        warning = 0;
    }
    public void get_data(){
        System.out.println("Enter member id: ");
        member_id = sc.nextInt();
        sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?"); 
        System.out.println("Enter member name: ");
        member_name = sc.nextLine();
        System.out.println("Enter address: ");
        address = sc.nextLine();
        System.out.println("Enter email: ");
        email = sc.nextLine();
        System.out.println("Enter Phone No: ");
        phoneno = sc.nextLine();
        books_issued = 0;
        books_returned = 0;
        warning = 0;
    }
    public void put_data(){
        System.out.println("Member id: " + member_id);
        System.out.println("Member Name: " + member_name);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("Phone No: " + phoneno);
        System.out.println("Book Issued: " + books_issued);
        System.out.println("Books Returned: " + books_returned);
        System.out.println("Warning: " + warning);
    }
    int ret_memid(){
        return member_id;
    }
    String ret_memname(){
        return member_name;
    }
    int ret_issue(){
        return books_issued;
    }
    int ret_returned(){
        return books_returned;
    }
    void issued(){
        books_issued++;
    }
    void returned(){
        books_returned++;
    }
    void warn(){
        warning++;
    }
}
class issue_records extends books implements libman, Serializable{
    int member_id;
    String mem_name;
    UUID reference_id;
    LocalDate  issue_date;
    private static final long serialVersionUID = 1L;
    issue_records(){
        member_id = 0;
        mem_name = new String();
        reference_id = UUID.randomUUID();
        issue_date = LocalDate.now();
        book_name = new String();
        book_id = 0;
    }
    public void get_data(UUID ref_id, int bk_id, String bk_name, int m_id,String m_name){
        reference_id = UUID.randomUUID();
        book_id = bk_id;
        book_name = bk_name;
        issue_date = LocalDate.now();
        member_id = m_id;
        mem_name = m_name;
    }
    public void put_data(){
        System.out.println("Ref ID: "+reference_id+", Book ID: "+book_id+", Book Name: "+ book_name+", Issue Date: "+issue_date+", Issued To: "+ mem_name);
    }
}
class return_records extends issue_records implements Serializable,libman{
    LocalDate return_date;
    private static final long serialVersionUID = 1L;
    return_records(){
        member_id = 0;
        mem_name = new String();
        reference_id = UUID.randomUUID();
        return_date = LocalDate.now();
        book_name = new String();
        book_id = 0;
    }
    public void get_data(String ref_id, int bk_id, String bk_name, int m_id,String m_name){
        reference_id = UUID.randomUUID();
        book_id = bk_id;
        book_name = bk_name;
        return_date = LocalDate.now();
        member_id = m_id;
        mem_name = m_name;
    }
    public void put_data(){
        System.out.println("Ref ID: "+reference_id+", Book ID: "+book_id+", Book Name: "+ book_name+", Return Date: "+return_date+", Returned By: "+ mem_name);
    }
}
class record_check implements Serializable{
    int mem_id;
    int book_id;
    String status;
    record_check(){
        mem_id = 0;
        book_id = 0;
        status = new String();
    }
    void insert_record(int m, int b, String s){
        mem_id = m;
        book_id = b;
        status = s;
    }
    int check_record(){
        if(status.equals("issued")){
            return 1;
        }
        else{
            return 0;
        }
    }
    int ret_memid(){
        return mem_id;
    } 
    int book_id(){
        return book_id;
    }
    void update(){
        status = "issued";
    }
    void update(int i){
        status = "returned";
    }  
}
public class librarymanager{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int choice,dummy=1;
        ArrayList<books> booklist = new ArrayList<>();
        ArrayList<members> memberlist = new ArrayList<>();
        ArrayList<issue_records> issue_records_list = new ArrayList<>();
        ArrayList<return_records> return_records_list = new ArrayList<>();
        ArrayList<record_check> record_check_list = new ArrayList<>();
        while(true){
            System.out.print("\033[H\033[2J");//For clearing the terminal
            System.out.flush();//For clearing the terminal
            System.out.println("|-------------------------------------------------------------------------|");
            System.out.println("|                         Library Managemnet System                       |");
            System.out.println("|-------------------------------------------------------------------------|");
            System.out.println("|                         1)Add Member                                    |");
            System.out.println("|                         2)Add Book                                      |");
            System.out.println("|                         3)View All Members                              |");
            System.out.println("|                         4)View All Books                                |");
            System.out.println("|                         5)Search Book                                   |");
            System.out.println("|                         6)Search Member                                 |");
            System.out.println("|                         7)Delete Book                                   |");
            System.out.println("|                         8)Delete Member                                 |");
            System.out.println("|                         9)Edit Book                                     |");
            System.out.println("|                         10)Edit Member                                  |");
            System.out.println("|                         11)Issue Book                                   |");
            System.out.println("|                         12)Return Book                                  |");
            System.out.println("|                         13)Issue Records                                |");
            System.out.println("|                         14)Return Records                               |");
            System.out.println("|                         15)Exit                                         |");
            System.out.println("|-------------------------------------------------------------------------|");
            System.out.print("                         Enter your choice: ");
            choice = sc.nextInt();
            if(choice==1){
                try{
                    FileInputStream fi = new FileInputStream("members.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    memberlist = (ArrayList) oi.readObject();
                    fi.close(); 
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }  
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Add Member");
                members mem = new members();
                mem.get_data();
                memberlist.add(mem);
                try {
                    File fff = new File("members.txt");
                    fff.createNewFile();
                    FileOutputStream fout = new FileOutputStream(fff);
                    ObjectOutputStream obout= new ObjectOutputStream(fout);
                    obout.writeObject(memberlist);
                    obout.close();
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
                System.out.println("Successfully Added");
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();  
            }
            else if(choice==2){
                try{
                    FileInputStream fi = new FileInputStream("books.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    booklist = (ArrayList) oi.readObject();
                    fi.close(); 
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }  
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Add Book");
                books book = new books();
                book.get_data();
                booklist.add(book);
                try {
                    File fff = new File("books.txt");
                    fff.createNewFile();
                    FileOutputStream fout = new FileOutputStream(fff);
                    ObjectOutputStream obout= new ObjectOutputStream(fout);
                    obout.writeObject(booklist);
                    obout.close();
                    fout.close();
                } catch (IOException e) {
                    System.out.println(e);
                } 
                System.out.println("Successfully Added");
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();  
            }
            else if(choice==3){
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("View All Members");
                System.out.println();
                try{
                    FileInputStream fi = new FileInputStream("members.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    memberlist = (ArrayList) oi.readObject();
                    for(members m : memberlist){
                        m.put_data();
                        System.out.println();
                    }
                    fi.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }  
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();  
            }
            else if(choice==4){
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("View All Books");
                System.out.println();
                try{
                    FileInputStream fi = new FileInputStream("books.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    booklist = (ArrayList) oi.readObject();
                    for(books b : booklist){
                        b.put_data();
                        System.out.println();
                    }
                    fi.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }  
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();  
            }
            else if(choice==5){
                int key,found = 0;
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Search Book");
                System.out.println();
                System.out.println("Enter book ID: ");
                key = sc.nextInt();
                System.out.println("Search Result: ");
                System.out.println();
                try{
                    FileInputStream fi = new FileInputStream("books.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    booklist = (ArrayList) oi.readObject();
                    for(books b : booklist){
                        if(b.ret_bookid()==key){
                            b.put_data();
                            found = 1;
                        }
                    }
                    if(found==0){
                        System.out.println("Book not found.");
                    }
                    fi.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }  
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();  
            }
            else if(choice==6){
                int key,found = 0;
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Search Member");
                System.out.println();
                System.out.println("Enter Member ID: ");
                key = sc.nextInt();
                System.out.println("Search Result: ");
                System.out.println();
                try{
                    FileInputStream fi = new FileInputStream("members.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    memberlist = (ArrayList) oi.readObject();
                    for(members m : memberlist){
                        if(m.ret_memid()==key){
                            m.put_data();
                            found = 1;
                            System.out.println();
                        }
                    }
                    if(found==0){
                        System.out.println("Member not found.");
                    }
                    fi.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }  
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();  
            }
            else if(choice==7){
                int key,found = 0;
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Delete Book");
                System.out.println();
                System.out.println("Enter book ID: ");
                key = sc.nextInt();
                System.out.println("Following book will be removed: ");
                System.out.println();
                try{
                    FileInputStream fi = new FileInputStream("books.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    booklist = (ArrayList) oi.readObject();
                    for(books b : booklist){
                        if(b.ret_bookid()==key){
                            b.put_data();
                            booklist.remove(b);
                            found = 1;
                        }
                    }
                    if(found==0){
                        System.out.println("Book not found.");
                    }
                    fi.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                try{
                    FileOutputStream fout = new FileOutputStream("books.txt");
                    ObjectOutputStream obout= new ObjectOutputStream(fout);
                    obout.writeObject(booklist);
                    obout.close();
                    fout.close();
                }catch (IOException e) {
                    System.out.println(e);
                } 
                System.out.println("Successfully Deleted"); 
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();  
            }
            else if(choice==8){
                int key,found = 0;
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Delete Member");
                System.out.println();
                System.out.println("Enter memeber ID: ");
                key = sc.nextInt();
                System.out.println("Following memeber will be removed: ");
                System.out.println();
                try{
                    FileInputStream fi = new FileInputStream("members.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    memberlist = (ArrayList) oi.readObject();
                    for(members m : memberlist){
                        if(m.ret_memid()==key){
                            m.put_data();
                            memberlist.remove(m);
                            found = 1;
                        }
                    }
                    if(found==0){
                        System.out.println("Member not found.");
                    }
                    fi.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                try{
                    FileOutputStream fout = new FileOutputStream("members.txt");
                    ObjectOutputStream obout= new ObjectOutputStream(fout);
                    obout.writeObject(memberlist);
                    obout.close();
                    fout.close();
                }catch (IOException e) {
                    System.out.println(e);
                } 
                System.out.println("Successfully Deleted"); 
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();  
            }
            else if(choice==9){
                int key,found = 0;
                int i = 0, loc=0;
                books book = new books();
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Edit Book");
                System.out.println();
                System.out.println("Enter book ID: ");
                key = sc.nextInt();
                System.out.println("Following book will be edited: ");
                System.out.println();
                try{
                    FileInputStream fi = new FileInputStream("books.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    booklist = (ArrayList) oi.readObject();
                    for(books b : booklist){
                        if(b.ret_bookid()==key){
                            b.put_data();
                            booklist.remove(b);
                            found = 1;
                            loc = i;
                            System.out.println("Enter Updated Details: ");
                            book.get_data();
                            booklist.add(loc,book);
                        }
                        i++;
                    }
                    if(found==0){
                        //System.out.println("Book not found.");
                    }
                    fi.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                try{
                    FileOutputStream fout = new FileOutputStream("books.txt");
                    ObjectOutputStream obout= new ObjectOutputStream(fout);
                    obout.writeObject(booklist);
                    obout.close();
                    fout.close();
                }catch (IOException e) {
                    //System.out.println(e);
                } 
                System.out.println("Successfully Updated"); 
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();  
            }
            else if(choice==10){
                int key,found = 0;
                int i = 0, loc=0;
                members mem = new members();
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Edit Member");
                System.out.println();
                System.out.println("Enter member ID: ");
                key = sc.nextInt();
                System.out.println("Following member details will be edited: ");
                System.out.println();
                try{
                    FileInputStream fi = new FileInputStream("members.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    memberlist = (ArrayList) oi.readObject();
                    for(members m : memberlist){
                        if(m.ret_memid()==key){
                            m.put_data();
                            memberlist.remove(m);
                            found = 1;
                            loc = i;
                            System.out.println("Enter Updated Details: ");
                            mem.get_data();
                            memberlist.add(loc,mem);
                        }
                        i++;
                    }
                    if(found==0){
                        System.out.println("Member not found.");
                    }
                    fi.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                try{
                    FileOutputStream fout = new FileOutputStream("members.txt");
                    ObjectOutputStream obout= new ObjectOutputStream(fout);
                    obout.writeObject(memberlist);
                    obout.close();
                    fout.close();
                }catch (IOException e) {
                    System.out.println(e);
                } 
                System.out.println("Successfully Updated"); 
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();  
            }
            if(choice==11){
                issue_records is_rec = new issue_records();
                record_check rec_check = new record_check();
                int keyb,keym,foundb = 0,foundm=0;
                int ib = 0,im=0, locb=0,locm=0;
                int confirm;
                int m_id=0,b_id=0;
                String m_name = new String();
                String b_name = new String();
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Issue Book");
                System.out.println();
                System.out.println("Enter book ID: ");
                keyb = sc.nextInt();
                System.out.println("Enter Member ID: ");
                keym = sc.nextInt();
                try{
                    FileInputStream fi = new FileInputStream("record_check.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    record_check_list = (ArrayList) oi.readObject();
                    fi.close(); 
                }
                catch(Exception e)
                {
                    //System.out.println(e);
                }
                int decide = 0;
                for(record_check r: record_check_list){
                    if(r.book_id==keyb && r.mem_id==keym){
                        if(r.check_record()==1){
                            decide = 1;
                        }
                    }
                }
                if(decide==0){
                    System.out.println("Following book will be issued: ");
                    System.out.println();
                    try{
                        FileInputStream fi = new FileInputStream("books.txt");
                        ObjectInputStream oi = new ObjectInputStream(fi);
                        booklist = (ArrayList) oi.readObject();
                        for(books b : booklist){
                            if(b.ret_bookid()==keyb){
                                b.put_data();
                                b_id = b.ret_bookid();
                                b_name = b.ret_bookname();
                                foundb=1;
                                System.out.println();
                            }
                        }
                        if(foundb==0){
                            System.out.println("Book not found.");
                        }
                        fi.close();
                    }
                    catch(Exception e)
                    {
                        //System.out.println(e);
                    }
                    try{
                        FileInputStream fi = new FileInputStream("members.txt");
                        ObjectInputStream oi = new ObjectInputStream(fi);
                        memberlist = (ArrayList) oi.readObject();
                        for(members m : memberlist){
                            if(m.ret_memid()==keym){
                                m.put_data();
                                m_id = m.ret_memid();
                                m_name = m.ret_memname();
                                foundm = 1;
                                System.out.println();
                            }
                        }
                        if(foundm==0){
                            System.out.println("Member not found.");
                        }
                        fi.close();
                    }
                    catch(Exception e)
                    {
                        //System.out.println(e);
                    }
                    System.out.println("Enter any integer to confirm: ");
                    confirm = sc.nextInt();
                    if(foundb==1 &&foundm==1){
                        try{
                            FileInputStream fi = new FileInputStream("books.txt");
                            ObjectInputStream oi = new ObjectInputStream(fi);
                            booklist = (ArrayList) oi.readObject();
                            for(books b : booklist){
                                if(b.ret_bookid()==keyb){
                                    booklist.remove(b);
                                    b.issued();
                                    locb = ib;
                                    booklist.add(locb,b);
                                }
                                ib++;
                            }
                            fi.close();
                        }
                        catch(Exception e)
                        {
                            //System.out.println(e);
                        }
                        try{
                            FileOutputStream fout = new FileOutputStream("books.txt");
                            ObjectOutputStream obout= new ObjectOutputStream(fout);
                            obout.writeObject(booklist);
                            obout.close();
                            fout.close();
                        }catch (IOException e) {
                            //System.out.println(e);
                        }
                        try{
                            FileInputStream fi = new FileInputStream("members.txt");
                            ObjectInputStream oi = new ObjectInputStream(fi);
                            memberlist = (ArrayList) oi.readObject();
                            for(members m : memberlist){
                                if(m.ret_memid()==keym){
                                    memberlist.remove(m);
                                    m.issued();
                                    locm=im;
                                    memberlist.add(locm,m);
                                }
                                im++;
                            }
                            fi.close();
                        }
                        catch(Exception e)
                        {
                            //System.out.println();
                        }
                        try{
                            FileOutputStream fout = new FileOutputStream("members.txt");
                            ObjectOutputStream obout= new ObjectOutputStream(fout);
                            obout.writeObject(memberlist);
                            obout.close();
                            fout.close();
                        }catch (IOException e) {
                            //System.out.println(e);
                        }
                        System.out.println("Successfully Issued");
                        try{
                            FileInputStream fi = new FileInputStream("issuerecords.txt");
                            ObjectInputStream oi = new ObjectInputStream(fi);
                            issue_records_list = (ArrayList) oi.readObject();
                            fi.close(); 
                        }
                        catch(Exception e)
                        {
                            //System.out.println(e);
                        }
                        is_rec.get_data(UUID.randomUUID(),b_id,b_name,m_id,m_name);
                        issue_records_list.add(is_rec);
                        rec_check.insert_record(m_id, b_id, "issued");
                        record_check_list.add(rec_check);
                        try {
                            FileOutputStream fout = new FileOutputStream("issuerecords.txt");
                            ObjectOutputStream obout= new ObjectOutputStream(fout);
                            obout.writeObject(issue_records_list);
                            obout.close();
                            fout.close();
                        } catch (IOException e) {
                            //e.printStackTrace();
                        }
                        try {
                            FileOutputStream fout = new FileOutputStream("record_check.txt");
                            ObjectOutputStream obout= new ObjectOutputStream(fout);
                            obout.writeObject(record_check_list);
                            obout.close();
                            fout.close();
                        } catch (IOException e) {
                            //e.printStackTrace();
                        }   
                        is_rec.put_data(); 
                    }
                    System.out.println("Enter any integer to continue....");
                    dummy = sc.nextInt();
                }
                else if(decide==1){
                    System.out.println("Operation Failed!The book is already issued to this person.");
                    System.out.println("Enter any integer to continue....");
                    dummy = sc.nextInt();
                }  
            }
            else if(choice==12){
                return_records ret_rec = new return_records();
                int keyb,keym,foundb = 0,foundm=0;
                int ib = 0,im=0, locb=0,locm=0;
                int confirm;
                int m_id=0,b_id=0;
                String m_name = new String();
                String b_name = new String();
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Return Book");
                System.out.println();
                System.out.println("Enter book ID: ");
                keyb = sc.nextInt();
                System.out.println("Enter Member ID: ");
                keym = sc.nextInt();
                try{
                    FileInputStream fi = new FileInputStream("record_check.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    record_check_list = (ArrayList) oi.readObject();
                    fi.close(); 
                }
                catch(Exception e)
                {
                    //System.out.println(e);
                }
                int decide = 0;
                for(record_check r: record_check_list){
                    if(r.book_id==keyb && r.mem_id==keym){
                        if(r.check_record()==1){
                            decide = 1;
                        }
                    }
                }
                if(decide==1){
                    System.out.println("Following book will be returned: ");
                    System.out.println();
                    try{
                        FileInputStream fi = new FileInputStream("books.txt");
                        ObjectInputStream oi = new ObjectInputStream(fi);
                        booklist = (ArrayList) oi.readObject();
                        for(books b : booklist){
                            if(b.ret_bookid()==keyb){
                                b.put_data();
                                b_id = b.ret_bookid();
                                b_name = b.ret_bookname();
                                foundb=1;
                                System.out.println();
                            }
                        }
                        if(foundb==0){
                            System.out.println("Book not found.");
                        }
                        fi.close();
                    }
                    catch(Exception e)
                    {
                        //System.out.println(e);
                    }
                    try{
                        FileInputStream fi = new FileInputStream("members.txt");
                        ObjectInputStream oi = new ObjectInputStream(fi);
                        memberlist = (ArrayList) oi.readObject();
                        for(members m : memberlist){
                            if(m.ret_memid()==keym){
                                m.put_data();
                                m_id = m.ret_memid();
                                m_name = m.ret_memname();
                                foundm = 1;
                                System.out.println();
                            }
                        }
                        if(foundm==0){
                            System.out.println("Member not found.");
                        }
                        fi.close();
                    }
                    catch(Exception e)
                    {
                        //System.out.println(e);
                    }
                    System.out.println("Enter any integer to confirm: ");
                    confirm = sc.nextInt();
                    for(record_check r: record_check_list){
                        if(r.book_id==keyb && r.mem_id==keym){
                            if(r.check_record()==1){
                                r.update(1);
                            }
                        }
                    }
                    if(foundb==1 &&foundm==1){
                        try{
                            FileInputStream fi = new FileInputStream("books.txt");
                            ObjectInputStream oi = new ObjectInputStream(fi);
                            booklist = (ArrayList) oi.readObject();
                            for(books b : booklist){
                                if(b.ret_bookid()==keyb){
                                    booklist.remove(b);
                                    b.returned();
                                    locb = ib;
                                    booklist.add(locb,b);
                                }
                                ib++;
                            }
                            fi.close();
                        }
                        catch(Exception e)
                        {
                            //System.out.println(e);
                        }
                        try{
                            FileOutputStream fout = new FileOutputStream("books.txt");
                            ObjectOutputStream obout= new ObjectOutputStream(fout);
                            obout.writeObject(booklist);
                            obout.close();
                            fout.close();
                        }catch (IOException e) {
                            //System.out.println(e);
                        }
                        try{
                            FileInputStream fi = new FileInputStream("members.txt");
                            ObjectInputStream oi = new ObjectInputStream(fi);
                            memberlist = (ArrayList) oi.readObject();
                            for(members m : memberlist){
                                if(m.ret_memid()==keym){
                                    memberlist.remove(m);
                                    m.returned();
                                    locm=im;
                                    memberlist.add(locm,m);
                                }
                                im++;
                            }
                            fi.close();
                        }
                        catch(Exception e)
                        {
                            //System.out.println();
                        }
                        try{
                            FileOutputStream fout = new FileOutputStream("members.txt");
                            ObjectOutputStream obout= new ObjectOutputStream(fout);
                            obout.writeObject(memberlist);
                            obout.close();
                            fout.close();
                        }catch (IOException e) {
                            //System.out.println(e);
                        }
                        System.out.println("Successfully Returned");
                        try{
                            File iff = new File("returnrecords.txt");
                            iff.createNewFile();
                            FileInputStream fi = new FileInputStream(iff);
                            ObjectInputStream oi = new ObjectInputStream(fi);
                            return_records_list = (ArrayList) oi.readObject();
                            fi.close(); 
                        }
                        catch(Exception e)
                        {
                            //System.out.println(e);
                        }
                        ret_rec.get_data(UUID.randomUUID(),b_id,b_name,m_id,m_name);
                        return_records_list.add(ret_rec);
                        try {
                            FileOutputStream fout = new FileOutputStream("returnrecords.txt");
                            ObjectOutputStream obout= new ObjectOutputStream(fout);
                            obout.writeObject(return_records_list);
                            obout.close();
                            fout.close();
                        } catch (IOException e) {
                           // e.printStackTrace();
                        }
                        try {
                            FileOutputStream fout = new FileOutputStream("record_check.txt");
                            ObjectOutputStream obout= new ObjectOutputStream(fout);
                            obout.writeObject(record_check_list);
                            obout.close();
                            fout.close();
                        } catch (IOException e) {
                            //e.printStackTrace();
                        }      
                        ret_rec.put_data(); 
                    }
                    System.out.println("Enter any integer to continue....");
                    dummy = sc.nextInt();
                }
                else if(decide==0){
                    System.out.println("Operation Failed! The book is not issued to this person.");
                    System.out.println("Enter any integer to continue....");
                    dummy = sc.nextInt();
                }  
            }
            else if(choice==13){
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Issue Records");
                System.out.println();
                try{
                    FileInputStream fi = new FileInputStream("issuerecords.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    issue_records_list = (ArrayList) oi.readObject();
                    for(issue_records i: issue_records_list){
                        i.put_data();
                        System.out.println();
                    }
                    fi.close(); 
                }
                catch(Exception e)
                {
                    //System.out.println(e);
                }
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();
            }
            else if(choice==14){
                System.out.print("\033[H\033[2J");//For clearing the terminal
                System.out.flush();//For clearing the terminal
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("|                         Library Managemnet System                       |");
                System.out.println("|-------------------------------------------------------------------------|");
                System.out.println("Return Records");
                System.out.println();
                try{
                    FileInputStream fi = new FileInputStream("returnrecords.txt");
                    ObjectInputStream oi = new ObjectInputStream(fi);
                    return_records_list = (ArrayList) oi.readObject();
                    for(return_records r: return_records_list){
                        r.put_data();
                        System.out.println();
                    }
                    fi.close(); 
                }
                catch(Exception e)
                {
                    //System.out.println(e);
                }
                System.out.println("Enter any integer to continue....");
                dummy = sc.nextInt();
            }
            else if(choice==15){
                System.exit(0);
            }
            
        }
        
    }
}