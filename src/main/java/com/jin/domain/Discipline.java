package com.jin.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.jin.data.jpa.domain.BusinessEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "discipline")
public class Discipline implements BusinessEntity<Long> {

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

	
    @Column(name = "name", nullable = false)
    @Size(min=3, max=100)
    private String name;

    @Column(name = "text_book", nullable = false)
    @Size(min=3, max=100)
    private String textBook;
    
    @Column(name="priority", nullable = false)
    @Min(0)
    @Max(10)
    private int priority = 5;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
      name="student_discipline",
      joinColumns=@JoinColumn(name="discipline_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "FK_DISCIPLINE")),
      inverseJoinColumns=@JoinColumn(name="student_id", referencedColumnName="id"))
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Student> students;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTextBook() {
		return textBook;
	}

	public void setTextBook(String textBook) {
		this.textBook = textBook;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Discipline [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", textBook=");
		builder.append(textBook);
		builder.append(", priority=");
		builder.append(priority);
		builder.append("]");
		return builder.toString();
	}

}
