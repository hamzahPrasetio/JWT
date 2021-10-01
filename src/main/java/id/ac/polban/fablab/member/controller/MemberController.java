package id.ac.polban.fablab.member.controller;

import id.ac.polban.fablab.member.model.Member;
import id.ac.polban.fablab.member.repository.MemberRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.Optional;

@RestController
@RequestMapping("")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    //add member
    @PostMapping("")
    public ResponseEntity<?> addMember (@RequestBody Member member) throws InstanceAlreadyExistsException {

        member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
        //TODO : check if exist dsb
        if(this.memberRepository.getByUsername(member.getUsername()).isPresent() ||
            this.memberRepository.getByEmail(member.getEmail()).isPresent() ||
            this.memberRepository.getByNim(member.getNim()).isPresent() ||
            this.memberRepository.getByWhatsapp(member.getWhatsapp()).isPresent()){
            return new ResponseEntity<>("Member already exist", HttpStatus.BAD_REQUEST);
        } else {
            this.memberRepository.save(member);
        }
        return ResponseEntity.ok(member);
    }
    //update member data
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateData(@PathVariable("id") String id, @RequestBody Member member){
        ObjectId objid = new ObjectId(id);
        Optional<Member> mbr = this.memberRepository.findById(objid);
        //TODO : check if new filed is null then not changing it
        mbr.get().setEmail(member.getEmail());
        mbr.get().setFullname(member.getFullname());
        mbr.get().setWhatsapp(member.getWhatsapp());
        mbr.get().setNim(member.getNim());
        mbr.get().setUsername(member.getUsername());
        mbr.get().setPassword(member.getPassword());
        mbr.get().setKtmUrl(member.getKtmUrl());
        mbr.get().setSelfieUrl(member.getSelfieUrl());
        this.memberRepository.save(mbr.get());
        return ResponseEntity.ok(mbr);
    }
    //update member profit, added or substracted
    @PostMapping("/update/profit/{id}")
    public ResponseEntity<?> updateProfit(@PathVariable("id") String id, @RequestParam long profitChange){
        ObjectId objid = new ObjectId(id);
        Optional<Member> mbr = this.memberRepository.findById(objid);
        mbr.get().setProfit(mbr.get().getProfit() + profitChange);
        this.memberRepository.save(mbr.get());
        return ResponseEntity.ok(mbr);
    }
    //update member point
    @PostMapping("/update/point/{id}")
    public ResponseEntity<?> updatePoint(@PathVariable("id") String id, @RequestParam int pointChange){
        ObjectId objid = new ObjectId(id);
        Optional<Member> mbr = this.memberRepository.findById(objid);
        mbr.get().setPoint(mbr.get().getPoint() + pointChange);
        this.memberRepository.save(mbr.get());
        return ResponseEntity.ok(mbr);
    }
    //update level
    @PostMapping("/update/level/{id}")
    public ResponseEntity<?> updateLevel(@PathVariable("id") String id, @RequestParam String newLevel){
        ObjectId objid = new ObjectId(id);
        Optional<Member> mbr = this.memberRepository.findById(objid);
        mbr.get().setLevel(mbr.get().getLevel() + newLevel);
        this.memberRepository.save(mbr.get());
        return ResponseEntity.ok(mbr);
    }
    //delete member
    @DeleteMapping("")
    public ResponseEntity<?> deleteMember (@RequestBody Member member){
        this.memberRepository.delete(member);
        return ResponseEntity.ok(member);
    }
    //get member by id (convert objid to str)
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getMemberByID(@PathVariable("id") String id){
        Optional<Member> mbr = this.memberRepository.findById(new ObjectId(id));
        return ResponseEntity.ok(mbr);
    }
    //get member by nim
    @GetMapping("/nim/{nim}")
    public ResponseEntity<?> getMemberByNim(@PathVariable("nim") String nim){
        Optional<Member> mbr = this.memberRepository.getByNim(nim);
        return ResponseEntity.ok(mbr);
    }
    //get member by username
    @GetMapping("/username/{uname}")
    public ResponseEntity<?> getMemberByUname(@PathVariable("uname") String uname){
        Optional<Member> mbr = this.memberRepository.getByUsername(uname);
        return ResponseEntity.ok(mbr);
    }
    //get member by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getMemberByEmail(@PathVariable("email") String email){
        Optional<Member> mbr = this.memberRepository.getByEmail(email);
        return ResponseEntity.ok(mbr);
    }
    //get member by whatsapp
    @GetMapping("/wa/{wa}")
    public ResponseEntity<?> getMemberByWA(@PathVariable("wa") String wa){
        Optional<Member> mbr = this.memberRepository.getByWhatsapp(wa);
        return ResponseEntity.ok(mbr);
    }
    @GetMapping("")
    public ResponseEntity<?> gea(){
        return ResponseEntity.ok(this.memberRepository.findAll());
    }

    /** AUTHENTICATION
     * return jwt **/
    //authenticate nim-password
    //authenticate username-password
    //authenticate email-password
    //authenticate whatsapp-password
}
