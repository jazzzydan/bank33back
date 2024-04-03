package ee.valiit.bank33back.business.location;


import ee.valiit.bank33back.business.location.dto.LocationInfo;
import ee.valiit.bank33back.infrastructure.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LocationController {

    private LocationService locationService;

    @GetMapping("/atm/locations/city/{cityId}")
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
