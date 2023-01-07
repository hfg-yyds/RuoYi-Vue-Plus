<template>
    <i-frame :src="url" />
  </template>
<script>
  import iFrame from "@/components/iFrame/index";
  export default {
    name: "Camunda",
    components: { iFrame },
    data() {
      return {
        url: "http://localhost:8080/camunda/app/welcome/default/#!/login"
      };
    },
  };
</script>
  