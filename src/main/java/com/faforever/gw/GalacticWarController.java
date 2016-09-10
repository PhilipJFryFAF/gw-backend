package com.faforever.gw;

import com.faforever.gw.tables.pojos.MapPool;
import org.jooq.DSLContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/gw")
public class GalacticWarController {

    @Resource
    private DSLContext dslContext;

    @RequestMapping(value = "mappool", method = RequestMethod.GET)
    public
    @ResponseBody
    List<MapPool> getMapPool() {
        return dslContext.selectFrom(Tables.MAP_POOL).fetchInto(MapPool.class);
    }
}
