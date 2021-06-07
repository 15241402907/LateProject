package com.lgd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback implements Serializable {

  private String id;
  private String title;
  private String content;
  private java.sql.Date feedTime;


}
