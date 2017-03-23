package Model;

public class Book {

    private String name;
    private String description;
    private Author author;
    private Publisher publisher;
    private float cost;
    private BookType bookType;
    private int ID;

    public Book() {

    }

    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return this.author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    /**
     *
     * @param publisher
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public float getCost() {
        return this.cost;
    }

    /**
     *
     * @param cost
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getID() {
        return this.ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
}
