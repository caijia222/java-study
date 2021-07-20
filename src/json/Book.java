package json;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

class Book{
    public long id;
    public String name;
    public BookAuthor author;
    @JsonDeserialize(using = IsbnDeserializer.class)
    public BigInteger isbn; // 自定义解析
    public List<String> tags;
    public LocalDate pubDate;
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", isbn=" + isbn + ", tags=" + tags
				+ ", pubDate=" + pubDate + "]";
	}
}

class BookAuthor{
	public String firstName;
	public String lastName;
	@Override
	public String toString() {
		return "BookAuthor [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
