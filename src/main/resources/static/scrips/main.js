Vue.createApp({
  data() {
    return {
      header: null,
      studentName: "Juan",
      studentCourse: "Bass",
      studentImg: "./assets/juan.png",
      studentReview: "Honestly I had an excellent learning in such a short time, the teachers are super friendly and great connoisseurs of music and rock. Thank you!",
      firstName: "",
      lastName: "",
      isLogin: false,
      courses: "",
      students: [],
      teachers: "",
      studentsMax4: [],
    }
  },

  created() {
    axios
      .get("/api/client/current").then(api => {
        this.firstName = api.data.firstName
        this.lastName = api.data.lastName
        this.isLogin = true;
      }),

      axios.get("/api/courses")
        .then(api => {
          this.courses = api.data
        }),

      axios.get("/api/clients")
        .then(api => {
          this.students = api.data
          for (i = 0; i <= 3; i++) {
            this.studentsMax4.push(this.students[i])
          }

        })

    axios.get("/api/teachers")
      .then(api => {
        this.teachers = api.data
      })

  },

  mounted() {
    this.$nextTick(function () {
      this.header = document.querySelector(".nav");
    })


  },
  methods: {
    filterStudent(studentCur) {
      let currentStudent = this.studentsMax4.filter(student => student.firstName === studentCur)
      let arrayRewiews = ["ds",'Honestly I had an excellent learning in such a short time, the teachers are super friendly and great connoisseurs of music and rock. Thank you!',
        'Since I was a child I always wanted to play the guitar, thanks to the guitar course that has become a reality. I am very happy with the quality of information and practical exercises that I have been given at the academy.',
        'I started taking classes 2 weeks ago. For the moment very happy. They are very didactic and with excellent predisposition.',
        'Thanks to the piano course of this academy, I can fulfill my dream of playing professionally in a rock band, excellent quality of teachers']
      this.studentName = currentStudent[0].firstName
      this.studentCourse = "Guitar"
      this.studentImg = `./assets/${studentCur}.png`
      this.studentReview = arrayRewiews[currentStudent[0].id]
    },

    subscribeEmail() {
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })

      Toast.fire({
        icon: 'success',
        title: 'Successfully subscribed!'
      })
    },
    logout() {
      axios
        .post("/api/logout")
        .then((response) => window.location.replace("./index.html"));
    },
  },
  computed: {
    headershow() {
      if (this.header != null) {
        window.addEventListener("scroll", () => {
          let header = this.header
          let scrolltop = document.documentElement.scrollTop;
          let top = header.offsetTop
          if (top + 100 <= scrolltop) {
            header.style.background = "#000000ff"
          }
          else {
            header.style.background = "rgba(0, 0, 0, 0.0001)"
          }
        });
      }
    },



  },
}).mount("#app")