package vn.edu.fpt.fb.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 *
 * @author : Portgas.D.Ace
 * @created : 12/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Table(name = "PROFILE")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Profile implements Serializable {
    private static final long serialVersionUID = 5088723963318493305L;
    @Id
    @Builder.Default
    @Column(name = "ID")
    private String profileId = UUID.randomUUID().toString();
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "AVATAR_FILE_ID")
    private String avatarFileId;
    @Column(name = "FULLNAME")
    private String fullName;
    @Column(name = "DOB")
    private LocalDateTime dob;
    @Column(name = "GENDER")
    private Integer gender;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "NICK_NAME")
    private String nickName;
    @Column(name = "STUDIED_AT")
    private String studiedAt;
    @Column(name = "LIVES_IN")
    private String livesIn;
    @Column(name = "WORKING_AT")
    private String workingAt;
    @Column(name = "BACKGROUD_FILE_ID")
    private String backGroundFileId;
    @Column(name = "RELATIONSHIP_STATUS")
    private Integer relationshipStatus;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;
}
