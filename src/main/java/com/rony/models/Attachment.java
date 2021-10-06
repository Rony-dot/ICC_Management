package com.rony.models;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="user_attachment")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Attachment implements Serializable {

    @Id
    @Column(name = "attachment_id", length = 20, nullable = false)
    private Long attachmentId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_title", nullable = true)
    private String fileTitle;

    @Column(name = "file_url", nullable = false)
    private String fileURL;

    @Column(name = "file_type")
    private String fileType;
}