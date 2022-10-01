package com.dalsom.management.character.lostarkapi;

import com.dalsom.management.character.CannotFindCharacterException;
import com.dalsom.management.common.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api/character")
@RequiredArgsConstructor
public class CharacterInfoApiController {

    private final CharacterInfoService characterInfoService;

    @GetMapping("{characterName}")
    @ResponseBody
    public CharacterInfoDto getCharacterInfo(@PathVariable("characterName") String characterName) throws IOException {
        return characterInfoService.createCharacterInfo(characterName);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse cannotFindCharacterExceptionHandler(CannotFindCharacterException e) {
        return new ErrorResponse(-1, e.getMessage());
    }
}
