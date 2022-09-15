package com.dalsom.management.guild;

import com.dalsom.management.guild.dto.GuildDetailDto;
import com.dalsom.management.guild.dto.GuildListDto;
import com.dalsom.management.guild.repository.GuildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guild")
@RequiredArgsConstructor
public class GuildController {

    private final GuildRepository guildRepository;

    @GetMapping
    public String list(Model model) {
        List<GuildListDto> guilds = guildRepository.findGuildsDto();
        model.addAttribute("guilds", guilds);
        return "guild/guild-list";
    }

    @GetMapping("{guildId}")
    public String details(@PathVariable Long guildId, Model model) {
        List<GuildDetailDto> guildDetailDtos = guildRepository.findGuildDetailById(guildId);
        if (guildDetailDtos.isEmpty()) {
            throw new RuntimeException("no guild found");
        }

        model.addAttribute("guild", guildDetailDtos.get(0));

        return "guild/guild-detail";
    }
}
