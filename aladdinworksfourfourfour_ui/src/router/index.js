import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import DataCenters from  '@/pages/DataCenters.vue';
import DataCenterDetail from  '@/pages/DataCenterDetail.vue';
import Racks from  '@/pages/Racks.vue';
import RackDetail from  '@/pages/RackDetail.vue';
import Switchs from  '@/pages/Switchs.vue';
import SwitchDetail from  '@/pages/SwitchDetail.vue';
import PowerSupplys from  '@/pages/PowerSupplys.vue';
import PowerSupplyDetail from  '@/pages/PowerSupplyDetail.vue';
import PowerStrips from  '@/pages/PowerStrips.vue';
import PowerStripDetail from  '@/pages/PowerStripDetail.vue';
import CoolingUnits from  '@/pages/CoolingUnits.vue';
import CoolingUnitDetail from  '@/pages/CoolingUnitDetail.vue';
import Generators from  '@/pages/Generators.vue';
import GeneratorDetail from  '@/pages/GeneratorDetail.vue';
import Sensors from  '@/pages/Sensors.vue';
import SensorDetail from  '@/pages/SensorDetail.vue';
import TemperatureSensors from  '@/pages/TemperatureSensors.vue';
import TemperatureSensorDetail from  '@/pages/TemperatureSensorDetail.vue';
import CurrentSensors from  '@/pages/CurrentSensors.vue';
import CurrentSensorDetail from  '@/pages/CurrentSensorDetail.vue';
import MonitoringPoints from  '@/pages/MonitoringPoints.vue';
import MonitoringPointDetail from  '@/pages/MonitoringPointDetail.vue';
import NetworkEquipments from  '@/pages/NetworkEquipments.vue';
import NetworkEquipmentDetail from  '@/pages/NetworkEquipmentDetail.vue';
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Alerts from  '@/pages/Alerts.vue';
import AlertDetail from  '@/pages/AlertDetail.vue';
import Maintenances from  '@/pages/Maintenances.vue';
import MaintenanceDetail from  '@/pages/MaintenanceDetail.vue';
import Issues from  '@/pages/Issues.vue';
import IssueDetail from  '@/pages/IssueDetail.vue';
import Alarms from  '@/pages/Alarms.vue';
import AlarmDetail from  '@/pages/AlarmDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import Reports from  '@/pages/Reports.vue';
import ReportDetail from  '@/pages/ReportDetail.vue';
import Tasks from  '@/pages/Tasks.vue';
import TaskDetail from  '@/pages/TaskDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/dataCenters',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/dataCenters',
		name: 'DataCenters',
		layout: DefaultLayout,
		component: DataCenters,
	},
	{
	    path: '/dataCenter/:dataCenterId', 
	    name: 'DataCenterDetail',
		layout: DefaultLayout,
	    component: DataCenterDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/racks',
		name: 'Racks',
		layout: DefaultLayout,
		component: Racks,
	},
	{
	    path: '/rack/:rackId', 
	    name: 'RackDetail',
		layout: DefaultLayout,
	    component: RackDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/switchs',
		name: 'Switchs',
		layout: DefaultLayout,
		component: Switchs,
	},
	{
	    path: '/switch/:switchId', 
	    name: 'SwitchDetail',
		layout: DefaultLayout,
	    component: SwitchDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerSupplys',
		name: 'PowerSupplys',
		layout: DefaultLayout,
		component: PowerSupplys,
	},
	{
	    path: '/powerSupply/:powerSupplyId', 
	    name: 'PowerSupplyDetail',
		layout: DefaultLayout,
	    component: PowerSupplyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerStrips',
		name: 'PowerStrips',
		layout: DefaultLayout,
		component: PowerStrips,
	},
	{
	    path: '/powerStrip/:powerStripId', 
	    name: 'PowerStripDetail',
		layout: DefaultLayout,
	    component: PowerStripDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/coolingUnits',
		name: 'CoolingUnits',
		layout: DefaultLayout,
		component: CoolingUnits,
	},
	{
	    path: '/coolingUnit/:coolingUnitId', 
	    name: 'CoolingUnitDetail',
		layout: DefaultLayout,
	    component: CoolingUnitDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/generators',
		name: 'Generators',
		layout: DefaultLayout,
		component: Generators,
	},
	{
	    path: '/generator/:generatorId', 
	    name: 'GeneratorDetail',
		layout: DefaultLayout,
	    component: GeneratorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/sensors',
		name: 'Sensors',
		layout: DefaultLayout,
		component: Sensors,
	},
	{
	    path: '/sensor/:sensorId', 
	    name: 'SensorDetail',
		layout: DefaultLayout,
	    component: SensorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/temperatureSensors',
		name: 'TemperatureSensors',
		layout: DefaultLayout,
		component: TemperatureSensors,
	},
	{
	    path: '/temperatureSensor/:temperatureSensorId', 
	    name: 'TemperatureSensorDetail',
		layout: DefaultLayout,
	    component: TemperatureSensorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/currentSensors',
		name: 'CurrentSensors',
		layout: DefaultLayout,
		component: CurrentSensors,
	},
	{
	    path: '/currentSensor/:currentSensorId', 
	    name: 'CurrentSensorDetail',
		layout: DefaultLayout,
	    component: CurrentSensorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/monitoringPoints',
		name: 'MonitoringPoints',
		layout: DefaultLayout,
		component: MonitoringPoints,
	},
	{
	    path: '/monitoringPoint/:monitoringPointId', 
	    name: 'MonitoringPointDetail',
		layout: DefaultLayout,
	    component: MonitoringPointDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/networkEquipments',
		name: 'NetworkEquipments',
		layout: DefaultLayout,
		component: NetworkEquipments,
	},
	{
	    path: '/networkEquipment/:networkEquipmentId', 
	    name: 'NetworkEquipmentDetail',
		layout: DefaultLayout,
	    component: NetworkEquipmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/alerts',
		name: 'Alerts',
		layout: DefaultLayout,
		component: Alerts,
	},
	{
	    path: '/alert/:alertId', 
	    name: 'AlertDetail',
		layout: DefaultLayout,
	    component: AlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/maintenances',
		name: 'Maintenances',
		layout: DefaultLayout,
		component: Maintenances,
	},
	{
	    path: '/maintenance/:maintenanceId', 
	    name: 'MaintenanceDetail',
		layout: DefaultLayout,
	    component: MaintenanceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/issues',
		name: 'Issues',
		layout: DefaultLayout,
		component: Issues,
	},
	{
	    path: '/issue/:issueId', 
	    name: 'IssueDetail',
		layout: DefaultLayout,
	    component: IssueDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/alarms',
		name: 'Alarms',
		layout: DefaultLayout,
		component: Alarms,
	},
	{
	    path: '/alarm/:alarmId', 
	    name: 'AlarmDetail',
		layout: DefaultLayout,
	    component: AlarmDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reports',
		name: 'Reports',
		layout: DefaultLayout,
		component: Reports,
	},
	{
	    path: '/report/:reportId', 
	    name: 'ReportDetail',
		layout: DefaultLayout,
	    component: ReportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tasks',
		name: 'Tasks',
		layout: DefaultLayout,
		component: Tasks,
	},
	{
	    path: '/task/:taskId', 
	    name: 'TaskDetail',
		layout: DefaultLayout,
	    component: TaskDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
