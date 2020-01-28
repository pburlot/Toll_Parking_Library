package com.microservice.tollparking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import com.microservice.tollparking.dao.ParkingDAOImpl;
import com.microservice.tollparking.exception.InvalidCarTypeException;
import com.microservice.tollparking.exception.NoSlotAvailableForTypeException;
import com.microservice.tollparking.exception.ParkingIDAlreadyExistingException;
import com.microservice.tollparking.exception.ParkingNotExistingException;
import com.microservice.tollparking.exception.ParkingsEmptyException;
import com.microservice.tollparking.exception.SlotNotExistingException;
import com.microservice.tollparking.exception.SlotNotInUseException;
import com.microservice.tollparking.exception.SlotTypeNotExistingExcpetion;
import com.microservice.tollparking.model.Parking;
import com.microservice.tollparking.model.Policy;
import com.microservice.tollparking.model.Slot;
import com.microservice.tollparking.model.SlotType;
import com.microservice.tollparking.processing.BillingResponse;
import com.microservice.tollparking.processing.ParkCarCreationRequest;
import com.microservice.tollparking.processing.ParkingCreationRequest;
import com.microservice.tollparking.processing.ParkingResponse;
import com.microservice.tollparking.processing.SlotCreationRequest;

public class TollparkingControllerTest {
	
    @Mock
    private ParkingDAOImpl parkingDAO;

    @InjectMocks
    private TollparkingController tollparkingController;

    private List<Parking> parkings;
    private List<Integer> parkingsId;

    @BeforeEach
    public void init() {
        setup();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getListParking_shouldReturnParkingList() {
        when(parkingDAO.findAll()).thenReturn(parkings);

        List<ParkingResponse> parkingsResponse = tollparkingController.ListParkings();

        assertNotNull(parkingsResponse);
        assertFalse(parkingsResponse.isEmpty());
        assertEquals(parkingsResponse.size(), 3);
        assertEquals(parkingsResponse.get(0).getId(), 1);
        assertEquals(parkingsResponse.get(1).getId(), 2);
        assertEquals(parkingsResponse.get(2).getId(), 3);
    }

    @Test
    public void getListParking_shouldThrowExceptionWhenListEmpty() {
        when(parkingDAO.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertThrows(ParkingsEmptyException.class, () -> tollparkingController.ListParkings());
    }


    @Test
    public void getParking_shouldReturnParking() {
        when(parkingDAO.findById(eq(1))).thenReturn(parkings.get(0));

        Parking parking = tollparkingController.DisplayParking(1);

        assertNotNull(parking);
        assertEquals(parking.getId(), 1);
    }

    @Test
    public void getParking_shouldThrowExceptionWhenParkingNotFound() {
        when(parkingDAO.findById(eq(1))).thenReturn(null);

        Assertions.assertThrows(ParkingNotExistingException.class, () -> tollparkingController.DisplayParking(1));
    }

    @Test
    public void addParking_shouldaddParking(){
        SlotCreationRequest slot1 = new SlotCreationRequest("Standard", 1);
        ParkingCreationRequest parkingCreationRequest = new ParkingCreationRequest(4, "Parking 4", null, Collections.singletonList(slot1));

        when(parkingDAO.getListOfParkingID()).thenReturn(parkingsId);

        tollparkingController.AddParking(parkingCreationRequest);

        verify(parkingDAO).save(any(Parking.class));
    }

    @Test
    public void addParking_shouldExceptionIfAlreadyExist(){
        SlotCreationRequest slot1 = new SlotCreationRequest("Standard", 1);
        ParkingCreationRequest parkingCreationRequest = new ParkingCreationRequest(3, "Parking 3", null, Collections.singletonList(slot1));

        when(parkingDAO.getListOfParkingID()).thenReturn(parkingsId);

        Assertions.assertThrows(ParkingIDAlreadyExistingException.class, () -> tollparkingController.AddParking(parkingCreationRequest));

    }

    @Test
    public void parkCar_shouldThrowExceptionIfParkingNotExist(){
        ParkCarCreationRequest parkCarCreationRequest = new ParkCarCreationRequest("Standard");
        when(parkingDAO.findById(eq(4))).thenReturn(null);

        Assertions.assertThrows(ParkingNotExistingException.class, () -> tollparkingController.ParkCar(4, parkCarCreationRequest));
    }


    @Test
    public void parkCar_shouldThrowExceptionIfSlotTypeNotExist(){
        ParkCarCreationRequest parkCarCreationRequest = new ParkCarCreationRequest("Non-Standard");
        when(parkingDAO.findById(eq(1))).thenReturn(parkings.get(0));

        Assertions.assertThrows(InvalidCarTypeException.class, () -> tollparkingController.ParkCar(1, parkCarCreationRequest));
    }

    @Test
    public void parkCar_shouldThrowExceptionIfSlotTypeNotAvailableInParking(){
        ParkCarCreationRequest parkCarCreationRequest = new ParkCarCreationRequest("Electric_50kw");
        when(parkingDAO.findById(eq(1))).thenReturn(parkings.get(0));

        Assertions.assertThrows(SlotTypeNotExistingExcpetion.class, () -> tollparkingController.ParkCar(1, parkCarCreationRequest));
    }

    @Test
    public void parkCar_shouldThrowExceptionIfNoFreeSlot(){
        ParkCarCreationRequest parkCarCreationRequest = new ParkCarCreationRequest("Electric_20kw");
        when(parkingDAO.findById(eq(1))).thenReturn(parkings.get(0));

        Assertions.assertThrows(NoSlotAvailableForTypeException.class, () -> tollparkingController.ParkCar(1, parkCarCreationRequest));
    }

    @Test
    public void leaveParking_shouldFreeTheSlot() {
        when(parkingDAO.findById(eq(1))).thenReturn(parkings.get(0));

        BillingResponse billingResponse = tollparkingController.LeaveParkingSlot(1, 1);

        assertNotNull(billingResponse);
        assertEquals(billingResponse.getBill(), 15.0f);
    }

    @Test
    public void leaveParking_shouldThrowExceptionIfParkingNotExist() {
        when(parkingDAO.findById(eq(4))).thenReturn(null);

        Assertions.assertThrows(ParkingNotExistingException.class, () -> tollparkingController.LeaveParkingSlot(4, 1));
    }

    @Test
    public void leaveParking_shouldThrowExceptionIfSlotNotExist() {
        when(parkingDAO.findById(eq(1))).thenReturn(parkings.get(0));

        Assertions.assertThrows(SlotNotExistingException.class, () -> tollparkingController.LeaveParkingSlot(1, 2));
    }

    @Test
    public void leaveParking_shouldThrowExceptionIfSlotIsFree() {
        when(parkingDAO.findById(eq(1))).thenReturn(parkings.get(0));

        Assertions.assertThrows(SlotNotInUseException.class, () -> tollparkingController.LeaveParkingSlot(1, 0));
    }

    private void setup() {
        Parking p1 = new Parking(1, "Parking 1", new Policy(15.0f));
        Parking p2 = new Parking(2, "Parking 2", null);
        Parking p3 = new Parking(3, "Parking 3", null);

        Map<SlotType, Set<Slot>> carParkingSlots = new HashMap<>();
        carParkingSlots.put(SlotType.Standard, Collections.singleton(new Slot(0, true)));
        Slot slot = new Slot(1, false);
        slot.setStartTimeOccupation(LocalDateTime.now().minusHours(1));
        carParkingSlots.put(SlotType.Electric_20kw, Collections.singleton(slot));
        p1.setCarParkingSlots(carParkingSlots);

        parkings = new ArrayList<>();
        parkings.add(p1);
        parkings.add(p2);
        parkings.add(p3);

        parkingsId = new ArrayList<>();
        parkingsId.add(1);
        parkingsId.add(2);
        parkingsId.add(3);
    }

}
