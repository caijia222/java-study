package xml;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

class Book{
    public long id;
    public String name;
    public String author;
    @JacksonXmlProperty(localName = "isbn")
    public BookAttr isbn;
    public List<String> tags;
    public String pubDate;
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", isbn=" + isbn + ", tags=" + tags
				+ ", pubDate=" + pubDate + "]";
	}
}

class BookAttr{
	@JacksonXmlProperty(isAttribute = true, localName = "lang")
	public String lang;
	@JacksonXmlText
	public String value;
	@Override
	public String toString() {
		return "BookAttr [lang=" + lang + ", value=" + value + "]";
	}
}
