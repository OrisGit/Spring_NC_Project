package com.nc.netcracker_project.desktop_rmi_client.util;

import com.nc.netcracker_project.server.services.event_service.AbstractEventListener;

public class EventListenerImpl extends AbstractEventListener {

    private Action updateDrugsAction;
    private Action updateDrugstoresAction;
    private Action updatePricesAction;
    private Action updatePharmTerGroupsAction;
    private Action updateProducersAction;

    public EventListenerImpl(Action updateDrugsAction, Action updateDrugstoresAction, Action updatePricesAction,
                             Action updatePharmTerGroupsAction, Action updateProducersAction) {
        super();
        this.updateDrugsAction = updateDrugsAction;
        this.updateDrugstoresAction = updateDrugstoresAction;
        this.updatePricesAction = updatePricesAction;
        this.updatePharmTerGroupsAction = updatePharmTerGroupsAction;
        this.updateProducersAction = updateProducersAction;
    }

    @Override
    public void updateDrugs() {
        updateDrugsAction.action();
    }

    @Override
    public void updateDrugstores() {
        updateDrugstoresAction.action();
    }

    @Override
    public void updatePrices() {
        updatePricesAction.action();
    }

    @Override
    public void updatePharmTerGroups() {
        updatePharmTerGroupsAction.action();
    }

    @Override
    public void updateProducers() {
        updateProducersAction.action();
    }
}
