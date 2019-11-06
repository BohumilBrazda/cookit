package cz.brazda.cookit.repository.entity;

import cz.brazda.cookit.common.IdElement;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

import javax.persistence.*;
import java.sql.Blob;
import java.util.UUID;

@Entity
@Table(name = "image")
public class Image implements IdElement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private	Long id;

	@Column(name = "image", columnDefinition="mediumblob")
	@Lob()
	private byte[] image;

	@ManyToOne
	@JoinColumn(name = "source")
	private Meal source;

	public Image() {
	}
	public byte[] getImage() {
		return image;
	}

	public Meal getSourceId() {
		return source;
	}

	public void setSourceId(Meal sourceId) {
		this.source = sourceId;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}