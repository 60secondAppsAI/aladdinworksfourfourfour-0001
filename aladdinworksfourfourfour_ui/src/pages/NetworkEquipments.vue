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
            <networkEquipment-table
            v-if="networkEquipments && networkEquipments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:networkEquipments="networkEquipments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-network-equipments="getAllNetworkEquipments"
             >

            </networkEquipment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import NetworkEquipmentTable from "@/components/NetworkEquipmentTable";
import NetworkEquipmentService from "../services/NetworkEquipmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    NetworkEquipmentTable,
  },
  data() {
    return {
      networkEquipments: [],
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
    async getAllNetworkEquipments(sortBy='networkEquipmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await NetworkEquipmentService.getAllNetworkEquipments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.networkEquipments.length) {
					this.networkEquipments = response.data.networkEquipments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching networkEquipments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching networkEquipment details:", error);
      }
    },
  },
  mounted() {
    this.getAllNetworkEquipments();
  },
  created() {
    this.$root.$on('searchQueryForNetworkEquipmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllNetworkEquipments();
    })
  }
};
</script>
<style></style>
