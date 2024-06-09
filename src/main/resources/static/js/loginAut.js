import axios from 'axios';


function LoginForm({ onSignupClick, onLoginSuccess }) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
  
    const handleLogin = async (e) => {
      e.preventDefault();
      try {
        const response = await axios.post('/api/auth/login', {
          email: email,
          password: password
        });
  
        if (response.status === 200) {
          console.log('Login successful');
          onLoginSuccess();
        }
  
      } catch (err) {
        console.error('Error during login', err);
        setError('Login failed. Please check your credentials and try again.');
      }
    };
}