package com.ticket.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "log")
@Entity()
@Data
public class Log {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "url", unique = true)
	private String url;

	@Column(name = "log")
	private String log;

	@Column(name = "startTime")
	private LocalDateTime startTime = LocalDateTime.now();

	@Column(name = "endTime")
	private LocalDateTime endTime;

	@Column(name = "duration")
	private String duration;

	@Column(name = "status")
	private String status;

	@Column(name = "groupedBy")
	private String groupedBy;

	@Override
	public String toString() {
		return "Log [id=" + id + ", url=" + url + ", log=" + log + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", duration=" + duration + ", status=" + status + ", groupedBy=" + groupedBy + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroupedBy() {
		return groupedBy;
	}

	public void setGroupedBy(String groupedBy) {
		this.groupedBy = groupedBy;
	}

	public Log() {
		super();
	}

}
