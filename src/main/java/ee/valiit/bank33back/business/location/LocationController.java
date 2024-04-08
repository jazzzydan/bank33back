package ee.valiit.bank33back.business.location;


import ee.valiit.bank33back.business.location.dto.LocationInfo;
import ee.valiit.bank33back.business.location.dto.LocationInfoExtended;
import ee.valiit.bank33back.infrastructure.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/atm")
public class LocationController {

    private LocationService locationService;

    @PostMapping("/location")
    @Operation(summary = "Uue pangaautomaadi lisamine.", description = "imageData ja transactionTypeName pole kohustuslikud väljad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Sellise nimega pangaautomaadi asukoht on juba süsteemis olemas", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void addAtmLocation(@RequestBody @Valid LocationInfoExtended locationInfoExtended) {
        locationService.addAtmLocation(locationInfoExtended);
    }

    @GetMapping("/location/{locationId}")
    @Operation(summary = "Leiab locationId abil ülesse pangaautomaadi asukoha info koos pildi infoga",
            description = "Kui pilti ei ole, siis imageData välja väärtus on tühi string")
    public LocationInfoExtended getAtmLocation(@PathVariable Integer locationId) {
        return locationService.getAtmLocation(locationId);
    }


    @PutMapping("/location/{locationId}")
    @Operation(summary = "Muudab olemasoleva pangaautomaadi andmeid (kirjutab üle).",
            description = "imageData ja transactionTypeName pole kohustuslikud väljad")
    public void updateAtmLocation(@PathVariable Integer locationId, @RequestBody LocationInfoExtended locationInfoExtended) {
        locationService.updateAtmLocation(locationId, locationInfoExtended);
    }


    @DeleteMapping("/location/{locationId}")
    @Operation(summary = "Pangaautomaadi eemaldamine locationId abil",
            description = "Andmebaasist reaalselt asukoha infot ei eemaldata, vaid see deaktiveeritakse")
    public void removeAtmLocation(@PathVariable Integer locationId) {
        locationService.removeAtmLocation(locationId);
    }

    @GetMapping("/locations/city/{cityId}")
    @Operation(summary = "Tagastab pangaatuomaatide asukohtade infot.",
            description = "Kui cityId on 0, siis tagastatakse kõik asukohad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Ei leitud ühtegi pangaautomaati",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public List<LocationInfo> findAtmLocations(@PathVariable Integer cityId) {
        return locationService.findAtmLocations(cityId);
    }


}
