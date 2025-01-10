import { render } from '@testing-library/react';
import { App } from 'antd';
import { BrowserRouter as Router } from 'react-router-dom';

test('renders learn react link', () => {
  render(
    <Router>
      <App />
    </Router>
  );
  // ... test assertions here
});
