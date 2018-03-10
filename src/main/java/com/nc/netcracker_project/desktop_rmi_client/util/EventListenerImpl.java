package com.nc.netcracker_project.desktop_rmi_client.util;

import com.nc.netcracker_project.server.controllers.rmi.AbstractEventListener;

public class EventListenerImpl extends AbstractEventListener {

    private Action updateDrugsAction;
    private Action updateDrugstoresAction;
    private Action updatePricesAction;
    private Action updateTherapheuticEffectsAction;
    private Action updatePharmachologicEffectsAction;

    public EventListenerImpl(Action updateDrugsAction, Action updateDrugstoresAction, Action updatePricesAction,
                             Action updateTherapheuticEffectsAction, Action updatePharmachologicEffectsAction) {
        super();
        this.updateDrugsAction = updateDrugsAction;
        this.updateDrugstoresAction = updateDrugstoresAction;
        this.updatePricesAction = updatePricesAction;
        this.updateTherapheuticEffectsAction = updateTherapheuticEffectsAction;
        this.updatePharmachologicEffectsAction = updatePharmachologicEffectsAction;
    }

    @Override
    public void updateDrugs() {
        updateDrugsAction.action();
    }

    @Override
    public void updateDrustores() {
        updateDrugstoresAction.action();
    }

    @Override
    public void updatePrices() {
        updatePricesAction.action();
    }

    @Override
    public void updateTherapheuticEffects() {
        updateTherapheuticEffectsAction.action();
    }

    @Override
    public void updatePharmachologicEffects() {
        updatePharmachologicEffectsAction.action();
    }
}
