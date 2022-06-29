Vue.createApp({
    data() {
        return{
            arrayObjectMerch: [],
    arrayObjectCourse: [],
    arrayJSONmerch: "",
    arrayJSONcourse: "",
    email: "",
    password: "",
    idTicket: "" 

        }
    },
    created(){
        this.arrayObjectMerch = [
            {
                "id": 1,
                "quantity": 1
            },
            {
                "id": 2,
                "quantity": 1
            }
        ]



        this.arrayObjectCourse = [] 
        this.arrayJSONmerch = JSON.stringify(this.arrayObjectMerch)
        this.email = "dsada@gmail.com"
        this.password = "1234"

        this.loading = false;
    },
    methods:{
        async login(){
            await axios.post("/api/login", `email=${this.email}&password=${this.password}`)
            .then(response =>{
                console.log(response)
                console.log("autenticado")
            })
        },
        async ticketCompra(){
            var data = JSON.stringify(this.arrayObjectMerch);
              
            //   var config = {
            //     method: 'post',
            //     url: ,
            //     headers: { 
            //       'Content-Type': 'application/json'
            //     },
            //     data : data
            //   };
              
            axios.post(`/api/ticket_transaction?idsCourses=${this.arrayObjectCourse}`, data, {headers:{'content-type':'application/json'}})
            .then(function (response) {
                var data1 = JSON.stringify(this.arrayObjectMerch);
                console.log(response.data)
                axios.post(`/pdf/generate/${response.data}`, data1, {headers:{'content-type':'application/json'}})
                .then(function (response) {
                    console.log("COMPLETE")
                })
                .catch(function (error) {
                    console.log(error);
                });


                // console.log(typeof response.data);
                // console.log("COMPLETE")
                
                })
            .catch(function (error) {
            console.log(error);
            });

        //     await axios.post("/api/ticket_transaction",{
        //         params: {
        //             idsCourses: this.arrayObjectCourse
        //         },
        //         data: this.arrayObjectMerch,
        //         dataType: "json",
        //         contentType:'application/json'
        //     })
        //     .then(response => {
        //      console.log("response")
        //     }
        //     )
        //     .catch(function (error) {
        //      if (error.response) {

        //        console.log(error.response.data);
        //        console.log(error.response.status);
        //        console.log(error.response.headers);
        //      } else if (error.request) {

        //        console.log("error.request");
        //      } else {
        //         console.log('Error F', error.message);
        //      }
        //      console.log(error.config);
        //    });
         }
    }
}).mount('#app')