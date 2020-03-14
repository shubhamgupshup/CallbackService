package io.gupshup.callbackService.callback;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CallbackService {


    @Autowired
    private CallbackRepository callbackRepository;

    public List<Callback> getAllCallbacks(){
        List<Callback> callbacks = new ArrayList<>();
        callbackRepository.findAll().forEach(callbacks::add);
        return callbacks;

    }

    public Optional<Callback> getCallback(long id){
        return callbackRepository.findById(id);
    }

    public Callback addCallback(Callback callback){
        callbackRepository.save(callback);
        return callback;
    }

    public Optional<Callback> deleteCallback(long id){
        callbackRepository.deleteById(id);
        return Optional.empty();
    }

    public Callback updateCallback(long id, Callback callback){
        callbackRepository.save(callback);
        return callback;
    }
}
