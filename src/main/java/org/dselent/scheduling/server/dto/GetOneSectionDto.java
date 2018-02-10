package org.dselent.scheduling.server.dto;

import javax.annotation.Generated;
import java.util.Objects;

public class GetOneSectionDto {
	
	private final Integer id;
    private final Integer section_number;
    private final String section_type;
    private final Integer instructor_info_id;
    private final String location;
    private final Boolean deleted;
    private final Integer course_info_id;
    private final Integer calendar_info_id;
    
    @Generated("SparkTools")
    private GetOneSectionDto(Builder builder) {
        // can add defaults if null for other places where the builder pattern is used

        this.id = builder.id;
        this.section_number = builder.section_number;
        this.section_type = builder.section_type;
        this.instructor_info_id = builder.instructor_info_id;
        this.location = builder.location;
        this.deleted = builder.deleted;
        this.course_info_id = builder.course_info_id;
        this.calendar_info_id = builder.calendar_info_id;
        
        if (this.id == null) {
            throw new IllegalStateException("id cannot be null");
        }
        
        if (this.section_number == null) {
            throw new IllegalStateException("sectionNumber cannot be null");
        }
        
        if (this.section_type == null) {
            throw new IllegalStateException("sectionType cannot be null");
        }
        
        if (this.instructor_info_id == null) {
            throw new IllegalStateException("instructorInfoId cannot be null");
        }
        
        if (this.location == null) {
            throw new IllegalStateException("location cannot be null");
        }
        
        if (this.deleted == null) {
            throw new IllegalStateException("deleted cannot be null");
        }
        
        if (this.course_info_id == null) {
            throw new IllegalStateException("courseInfoId cannot be null");
        }
        
        if (this.calendar_info_id == null) {
            throw new IllegalStateException("calendarInfoId cannot be null");
        }
        
    }
    

	public Integer getId() {
		return id;
	}

	public Integer getSection_number() {
		return section_number;
	}

	public String getSection_type() {
		return section_type;
	}

	public Integer getInstructor_info_id() {
		return instructor_info_id;
	}

	public String getLocation() {
		return location;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Integer getCourse_info_id() {
		return course_info_id;
	}

	public Integer getCalendar_info_id() {
		return calendar_info_id;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetOneSectionDto that = (GetOneSectionDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(section_number, that.section_number) &&
                Objects.equals(section_type, that.section_type) &&
                Objects.equals(instructor_info_id, that.instructor_info_id) &&
                Objects.equals(location, that.location) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(course_info_id, that.course_info_id) &&
                Objects.equals(calendar_info_id, that.calendar_info_id)
                ;
    }
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calendar_info_id == null) ? 0 : calendar_info_id.hashCode());
		result = prime * result + ((course_info_id == null) ? 0 : course_info_id.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instructor_info_id == null) ? 0 : instructor_info_id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((section_number == null) ? 0 : section_number.hashCode());
		result = prime * result + ((section_type == null) ? 0 : section_type.hashCode());
		return result;
	}


	@Override
	public String toString() {
		return "GetOneSectionDto [id=" + id + ", section_number=" + section_number + ", section_type=" + section_type
				+ ", instructor_info_id=" + instructor_info_id + ", location=" + location + ", deleted=" + deleted
				+ ", course_info_id=" + course_info_id + ", calendar_info_id=" + calendar_info_id + "]";
	}
	
	/**
     * Creates builder to build {@link GetOneScheduleDto}.
     *
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }
    
    @Generated("SparkTools")
    public static final class Builder {

    	private Integer id;
        private Integer section_number;
        private String section_type;
        private Integer instructor_info_id;
        private String location;
        private Boolean deleted;
        private Integer course_info_id;
        private Integer calendar_info_id;
        
        private Builder() {
        }
        
        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }
        
        public Builder withSectionNumber(Integer sectionNumber) {
            this.section_number = sectionNumber;
            return this;
        }
        
        public Builder withSectionType(String sectionType) {
            this.section_type = sectionType;
            return this;
        }
        
        public Builder withInstructorInfoId(Integer instructorInfoId) {
            this.instructor_info_id = instructorInfoId;
            return this;
        }
        
        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }
        
        public Builder withDeleted(Boolean deleted) {
            this.deleted = deleted;
            return this;
        }
        
        public Builder withCourseInfoId(Integer courseInfoId) {
            this.course_info_id = courseInfoId;
            return this;
        }
        
        public Builder withCalendarInfoId(Integer calendarInfoId) {
            this.calendar_info_id = calendarInfoId;
            return this;
        }

        public GetOneSectionDto build() {
            return new GetOneSectionDto(this);
        }
    }
	
	

}