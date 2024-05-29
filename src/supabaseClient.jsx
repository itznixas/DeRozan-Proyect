// src/supabaseClient.js
import { createClient } from '@supabase/supabase-js';

const supabaseUrl = 'https://wmnzzreruellnxowxvbm.supabase.co';
const supabaseKey = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Indtbnp6cmVydWVsbG54b3d4dmJtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTUyMjI2NDIsImV4cCI6MjAzMDc5ODY0Mn0.3J9Tuu29UQCEAwLeDGDeqwx29MmfKQ7hriDZ9QBg4Bg';
export const supabase = createClient(supabaseUrl, supabaseKey);
