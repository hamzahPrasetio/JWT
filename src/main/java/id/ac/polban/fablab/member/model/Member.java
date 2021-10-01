package id.ac.polban.fablab.member.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private ObjectId memberID;

    //member data
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String nim;
    private String whatsapp;
    private String ktmUrl;
    private String selfieUrl;

    //member secure data
    private String status;
    private long profit;
    private int point;
    private String level;

    public Member(Member m){
        this.setLevel(m.getLevel());
        this.setPoint(m.getPoint());
        this.setProfit(m.getProfit());
        this.setStatus(m.getStatus());

        this.setSelfieUrl(m.getSelfieUrl());
        this.setKtmUrl(m.getKtmUrl());
        this.setEmail(m.getEmail());
        this.setUsername(m.getUsername());
        this.setPassword(m.getPassword());
        this.setWhatsapp(m.getWhatsapp());
        this.setFullname(m.getFullname());
        this.setNim(m.getNim());
    }
}
