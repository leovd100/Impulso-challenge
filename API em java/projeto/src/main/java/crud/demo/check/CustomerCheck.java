package crud.demo.check;

import crud.demo.exception.HttpException;
import crud.demo.service.CustomerCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerCheck implements CustomerCheckService {

    @Override
    public ResponseEntity checkReturn(Object obj) {
        try {
                if (obj instanceof List) {
                    if (((List<?>) obj).isEmpty()) {
                        throw new HttpException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado");
                    }
                }
                if(obj instanceof Optional){
                    if(((Optional<?>) obj).isEmpty()){
                        throw new HttpException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado");
                    }
                }
                if (obj.equals(null)){
                    throw new HttpException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado");
                }

        }catch (HttpException ex){
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
        }catch (Exception ex){
            ex.getMessage();
        }
        return null;
    }







}
