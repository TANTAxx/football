package hu.football.services.users;

import hu.football.models.entities.users.Info;
import hu.football.respositories.users.InfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class InfoService {

    private final InfoRepository infoRepository;

    public List<Info> getAllInfo(){
        return infoRepository.findAll();
    }

    public List<Info> getAllActiveInfo(){
        return infoRepository.findAll();
    }
}
