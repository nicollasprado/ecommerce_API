/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
      extend: {
          colors: {
              'dark-purple': '#6B00F5',
              'light-purple': '#9852F5',
              'light-purple2': '#934BE0',
        },
      },
  },
  plugins: [],
}
