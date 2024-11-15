<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <alarm-table
            v-if="alarms && alarms.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:alarms="alarms"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-alarms="getAllAlarms"
             >

            </alarm-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AlarmTable from "@/components/AlarmTable";
import AlarmService from "../services/AlarmService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AlarmTable,
  },
  data() {
    return {
      alarms: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllAlarms(sortBy='alarmId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AlarmService.getAllAlarms(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.alarms.length) {
					this.alarms = response.data.alarms;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching alarms:", error);
        }
        
      } catch (error) {
        console.error("Error fetching alarm details:", error);
      }
    },
  },
  mounted() {
    this.getAllAlarms();
  },
  created() {
    this.$root.$on('searchQueryForAlarmsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAlarms();
    })
  }
};
</script>
<style></style>
