package id.ac.polban.fablab.member.repository;

import id.ac.polban.fablab.member.model.Member;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, ObjectId> {
    @Query("{ 'username' : {$regex : ?0, $options: 'i'} }")
    Optional<Member> getByUsername(String username);

    @Query("{ 'email' : ?0 }")
    Optional<Member> getByEmail(String email);

    @Query("{ 'nim' : ?0 }")
    Optional<Member> getByNim(String nim);

    @Query("{ 'whatsapp' : ?0 }")
    Optional<Member> getByWhatsapp(String whatsapp);
}
