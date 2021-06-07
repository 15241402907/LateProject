package com.lgd.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video implements Serializable {

  private String id;
  private String title;
  private String description;
  private String videoPath;
  private String coverPath;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date uploadTime;
  private String cateId;
  private String groupId;
  private String userId;

  private Integer likeCount;
  private Integer playCount;
  private List<Comment> comments;


}
